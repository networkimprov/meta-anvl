DESCRIPTION = "GraphicsMagick is an image conversion tool"
SECTION = "console/utils"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://Copyright.txt;md5=e651202f77c80e609d49b2fc8d583b9f"
DEPENDS = "lcms bzip2 jpeg libpng librsvg tiff zlib lzma libxml2"
RDEPENDS = "eglibc-gconv-utf-16"

PR = "r1"

inherit autotools binconfig pkgconfig

SRC_URI = "http://sourceforge.net/projects/graphicsmagick/files/graphicsmagick/${PV}/GraphicsMagick-${PV}.tar.gz \
          "
S = "${WORKDIR}/GraphicsMagick-${PV}"

SRC_URI[md5sum] = "f7e7326f84a8f5bb4194bb93f37b0305"
SRC_URI[sha256sum] = "e0d673f4e20709e9f4b58ba3b29435d7033f863400fd6d61bd4ad02668678891"

do_configure_prepend() {
	sed -i -e "s:freetype_config='':freetype_config='a':" configure
	sed -i -e 's:$LDFLAGS -L${freetype_exec_prefix}/lib:$LDFLAGS:' configure
	sed -i -e 's:CPPFLAGS="$CPPFLAGS -I${freetype_prefix}/include/freetype2":CPPFLAGS="$CPPFLAGS -I${STAGING_DIR}/${MACHINE}/${includedir}/freetype2":' configure

	sed -i -e 's:`xml2-config --prefix`:${STAGING_DIR}/${MACHINE}/${prefix}:' configure
}

do_configure () {

	oe_runconf --enable-shared --with-x=no --with-sysroot=${STAGING_DIR}/${MACHINE} --build=armel-linux
}

do_make () {
	sed -i -e 's:rpath="$compile_rpath$rpath":rpath=${libdir}:' libtool
	oe_runmake
}

FILES_${PN} += "${libdir}/GraphicsMagick-${PV}/config/ \
                ${datadir}/GraphicsMagick-${PV}/config/ \
               "
