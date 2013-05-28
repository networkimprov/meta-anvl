DESCRIPTION = "Graphviz"
HOMEPAGE = "http://graphviz.org"
LICENSE = "CPL"
LIC_FILES_CHKSUM = "file://cpl1.0.txt;md5=059e8cd6165cb4c31e351f2b69388fd9"

DEPENDS = "libtool-cross"

PR = "r0"

SRC_URI = "http://graphviz.org/pub/graphviz/stable/SOURCES/graphviz-2.30.1.tar.gz"

SRC_URI[md5sum] = "8130785a8f1fb8a57f6b839b617e85fa"
SRC_URI[sha256sum] = "775fb996987032d2eca2f1502c6f913e0b02fe640c124723e45db11daf46aec8"

#S = "${WORKDIR}/git/xapian-core"

inherit autotools gettext

# do_configure_prepend () {
#         ${S}/preautoreconf
# 	perl ${S}/generate-exceptions
# }

do_configure () {
 	oe_runconf
	touch ${S}/lib/gvpr/gdefs.h
}



BBCLASSEXTEND = "native"