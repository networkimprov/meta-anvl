SECTION = "console/network"
LICENSE = "GPL"
DEPENDS = "virtual/libiconv python heimdal-native"
DEPENDS_append = " qemu-native nativesdk-python "

SRC_URI = "http://samba.org/samba/ftp/stable/samba-${PV}.tar.gz \
           file://samba4-waf-prepend-path.patch \
	   file://incstr.patch \
	   file://exec-args.patch \
	   file://wscript-sysrootdir.patch \
	   file://append_prefix_to_python_paths.patch" 

S = "${WORKDIR}/samba-${PV}"

inherit qemu

# Large stack is required at least on x86_64 host, otherwise random segfaults appear:
QEMU = "${@qemu_target_binary(d)} ${QEMU_OPTIONS} -s 1048576 -L ${STAGING_DIR_TARGET} -E LD_LIBRARY_PATH=${STAGING_BASELIBDIR}"

EXTRA_OEMAKE += 'QEMU="${QEMU}"'

# The file system settings --foodir=dirfoo and overridden unconditionally
# in the samba config by --with-foodir=dirfoo - even if the --with is not
# specified!  Fix that here.  Set the privatedir to /etc/samba/private.

CONFFILES_${PN} = "/etc/samba/smb.conf"

# XXX Values of the directory paths passed into waf should be absolute,
# and waf itself should take care of prepending sysrootdir when needed
#
# XXX Investigate why parallel make and --disable-rpath don't work as
# expected
do_configure() {
	
	CPPPATH_ST="-I${STAGING_DIR}/${MACHINE}/%" \
        LIBPATH_ST="-L${STAGING_DIR}/${MACHINE}/%" \
        STATICLIBPATH_ST="-L${STAGING_DIR}/${MACHINE}/%" \
        RPATH_ST="-L${STAGING_DIR}/${MACHINE}/%" \
        LIB_ST="-L${STAGING_DIR}/${MACHINE}/%" \
	COMPILE_ET="${STAGING_DIR}/${BUILD_SYS}/usr/bin/compile_et" \
	ASN1_COMPILE="${STAGING_DIR}/${BUILD_SYS}/usr/libexec/heimdal/asn1_compile" \	
        buildtools/bin/waf configure --prefix=${D}${prefix} \
				     --libdir=${D}${libdir} \
				     --localstatedir=${D}var \
				     --sysconfdir=${D}etc \
				     --with-privatedir=${D}${sysconfdir}/samba/private \
			   	     --cross-compile \
				     --cross-execute "${QEMU}" \ 
				     --hostcc=${TARGET_PREFIX} \
				     --with-libiconv=${STAGING_LIBDIR}/.. \
				     --with-gettext=${STAGING_LIBDIR}/.. \
				     --enable-fhs \
				     --with-aio-support \
				     --bundled-libraries=!asn1_compile,!compile_et \
				     --with-sysrootdir=${STAGING_DIR_TARGET}
}

do_compile() {
        buildtools/bin/waf build ${PARALLEL_MAKE}
}

do_install() {
        buildtools/bin/waf install
}

# XXX remove the python2.7 and perl5 dependence
FILES_${PN}-dbg += "${libdir}/samba/.debug"
FILES_${PN}-dbg += "${libdir}/samba/service/.debug"
FILES_${PN}-dbg += "${libdir}/samba/nss_info/.debug"
FILES_${PN}-dbg += "${libdir}/samba/auth/.debug"
FILES_${PN}-dbg += "${libdir}/samba/process_model/.debug"
FILES_${PN}-dbg += "${libdir}/samba/ldb/.debug"
FILES_${PN}-dbg += "${libdir}/samba/vfs/.debug"
FILES_${PN}-dbg += "${libdir}/samba/gensec/.debug"
FILES_${PN}-dbg += "${libdir}/samba/bind9/.debug"
FILES_${PN}-dbg += "${libdir}/samba/idmap/.debug"
FILES_${PN}-dbg += "${libdir}/python2.7/site-packages/.debug"
FILES_${PN}-dbg += "${libdir}/python2.7/site-packages/samba/.debug"
FILES_${PN}-dbg += "${libdir}/python2.7/site-packages/dcerpc/.debug"
FILES_${PN}-dbg += "${libdir}/python2.7/site-packages/samba/samba3/.debug"
FILES_${PN}-dbg += "${libdir}/python2.7/site-packages/samba/dcerpc/.debug"
FILES_${PN}-dbg += "${libdir}/security/.debug"

#FILES_winbind += "${libdir}/winbind_krb5_locator.so"
#FILES_winbind += "${libdir}/security/pam_winbind.so"

#FILES_libtdb += "${libdir}/libtdb.so.*"

FILES_${PN} += "${libdir}/samba"
FILES_${PN} += "/usr/share/samba"
FILES_${PN} += "/usr/share/perl5/Parse"
FILES_${PN} += "${libdir}/python2.7/site-packages/samba"
FILES_${PN} += "${libdir}/python2.7/site-packages/talloc.so"
FILES_${PN} += "${libdir}/python2.7/site-packages/ldb.so"
FILES_${PN} += "${libdir}/python2.7/site-packages/_tevent.so"
FILES_${PN} += "${libdir}/python2.7/site-packages/tevent.so"
FILES_${PN} += "${libdir}/python2.7/site-packages/tevent.py"
FILES_${PN} += "${libdir}/python2.7/site-packages/tdb.so"
FILES_${PN} += "${libdir}/security/pam_smbpass.so"
FILES_${PN} += "${libdir}/security/pam_winbind.so"
FILES_${PN} += "${libdir}/winbind_krb5_locator.so"
FILES_${PN} += "${libdir}/mit_samba.so"
FILES_${PN} += "/lib/systemd/system"
FILES_${PN} += "/usr/lib/tmpfiles.d"


do_install_append() {
	# Install systemd files
	install -d ${D}/lib/systemd/system
	install -d ${D}/usr/lib/tmpfiles.d
	install -D -m 644 ${WORKDIR}/samba-${PV}/packaging/systemd/*.service ${D}/lib/systemd/system/
	install -D -m 644 ${WORKDIR}/samba-${PV}/packaging/systemd/samba.conf.tmp ${D}/usr/lib/tmpfiles.d/

	# Install smb.conf file
	install -D -m 644 ${WORKDIR}/samba-${PV}/packaging/LSB/smb.conf ${D}${sysconfdir}/samba/smb.conf

	install -d ${D}/var/log/samba
	install -d ${D}/var/spool/samba

#	install -d ${D}${libdir}/pkgconfig/
#	cp ${WORKDIR}/samba-${PV}/tdb.pc ${D}${libdir}/pkgconfig/

#	install -D -m 755 ${WORKDIR}/init.samba ${D}${sysconfdir}/init.d/samba
#	install -D -m 755 ${WORKDIR}/init.winbind ${D}${sysconfdir}/init.d/winbind

#	install -D -m 644 ${WORKDIR}/volatiles.03_samba ${D}${sysconfdir}/default/volatiles/volatiles.03_samba

	# Install other stuff not installed by "make install"
#	install -m 0644 ${WORKDIR}/${PN}-${PV}/nsswitch/libnss_winbind.so ${D}${libdir}/libnss_winbind.so.2
#	install -m 0644 ${WORKDIR}/${PN}-${PV}/nsswitch/libnss_wins.so ${D}${libdir}/libnss_wins.so.2
}



#pkg_postinst_winbind () {
#    # add wins to the list of resolvers
#    ns=$D${sysconfdir}/nsswitch.conf
#    if ! grep "hosts:.*wins" $ns > /dev/null; then
#	hosts="`grep '^hosts:' $ns`"
#	hosts=`echo "$hosts" | sed 's/\[/\\\\[/g; s/\]/\\\\]/g'`
#	sed -i "s/$hosts/$hosts wins/" "$ns"
#    fi
#}
