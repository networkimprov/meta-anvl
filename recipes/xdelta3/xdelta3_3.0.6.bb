DESCRIPTION = "xdelta3 binary diff library and tool"
HOMEPAGE = "http://xdelta.org/"
BUGTRACKER = "http://code.google.com/p/xdelta/issues/list/"
LICENSE = "GPLv2 & GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=393a5ca445f6965873eca0259a17f833"

# PR = "r0"

SRC_URI = "http://xdelta.googlecode.com/files/xdelta3-${PV}.tar.gz"

SRC_URI[md5sum] = "652048ab4a7e5f927bce045228a17b31"
SRC_URI[sha256sum] = "b9a439c27c26e8397dd1b438a2fac710b561e0961fe75682230e6c8f69340da5"

inherit autotools
