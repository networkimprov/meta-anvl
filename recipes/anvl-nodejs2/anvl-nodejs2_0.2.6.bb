DESCRIPTION = "nodeJS Evented I/O for V8 JavaScript"
HOMEPAGE = "http://nodejs.org"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8a8e153fdd2b18cad53828e8b5cdc897"

DEPENDS = "openssl"

SRC_URI = " \
  http://nodejs.org/dist/node-v${PV}.tar.gz \
  file://add_arm_build_target_${PV}.patch \
  file://libev-cross-cc_${PV}.patch \
  file://node-build-fixes_${PV}.patch \
"
SRC_URI[md5sum] = "b1c50ceb43bee1b221be210b7bc7a216"
SRC_URI[sha256sum] = "e97fe9c81ff4b569ae9a0d46e64a0572a1f171293573a5b5290bcc3996a19701"

S = "${WORKDIR}/node-v${PV}"

# v8 errors out if you have set CCACHE
CCACHE = ""

export prefix = "/opt/node/${PV}"
export exec_prefix = "/opt/node/${PV}"

do_configure () {
	sed -i -e 's:/usr/lib:${STAGING_LIBDIR}:g' wscript
	sed -i -e 's:/usr/local/lib:${STAGING_LIBDIR}:g' wscript
	./configure --prefix=${prefix} --without-snapshot
}

do_compile () {
	oe_runmake
}

do_install () {
	oe_runmake install DESTDIR=${D}
}

#FILES_${PN}-dbg += "/node/${PV}/bin/.debug"
FILES_${PN} += "/opt/node/${PV}"

BBCLASSEXTEND = "native"