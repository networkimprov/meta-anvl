DESCRIPTION = "Heimdal, a replacement for MIT Kerberos"
HOMEPAGE = "http://h5l.org"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d2c6f8cfe82d4fdd74355866f0c14d3f"
DEPENDS = "libxt"

PR = "r0"

SRC_URI = "http://www.h5l.org/dist/src/heimdal-1.5.3.tar.gz"

SRC_URI[md5sum] = "30b379e3de12f332fbd201131f02ffca"
SRC_URI[sha256sum] = "aac27bedb33c341b6aed202af07ccc816146a893148721f8123abbbf93bbfea5"

inherit autotools gettext

do_configure_prepend() {
        autoreconf -isf
}

do_configure() {
  	oe_runconf --disable-heimdal-documentation
}

BBCLASSEXTEND = "native"