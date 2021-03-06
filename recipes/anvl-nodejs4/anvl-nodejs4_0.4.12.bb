DESCRIPTION = "nodeJS Evented I/O for V8 JavaScript"
HOMEPAGE = "http://nodejs.org"
LICENSE = "MIT & Zlib"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d6237f3a840aef5b7880fb4e49eecfe5"

DEPENDS = "openssl"

SRC_URI = " \
  http://nodejs.org/dist/node-v${PV}.tar.gz \
  file://libev-cross-cc_${PV}.patch \
"
SRC_URI[md5sum] = "a6375eaa43db5356bf443e25b828ae16"
SRC_URI[sha256sum] = "c01af05b933ad4d2ca39f63cac057f54f032a4d83cff8711e42650ccee24fce4"

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

FILES_${PN}-dbg += "/opt/node/${PV}/bin/.debug"
FILES_${PN} += "/opt/node/${PV}"

# BBCLASSEXTEND = "native"



