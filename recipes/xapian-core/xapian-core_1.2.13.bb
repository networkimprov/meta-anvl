DESCRIPTION = "Xapian-core search engine library"
HOMEPAGE = "http://xapian.org/"
BUGTRACKER = "http://trac.xapian.org/"
LICENSE = "GPLv2 & GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=4325afd396febcb659c36b49533135d4"

PROVIDES += "xapian-core-dev"

PR = "r0"

SRC_URI = "http://oligarchy.co.uk/xapian/${PV}/xapian-core-${PV}.tar.gz"

SRC_URI[md5sum] = "d6e15fff5ecae1c03171d64c45ef9685"
SRC_URI[sha256sum] = "8829ca8ebb4ab97129b8a237541f6aa62a3f4dc1fcd503bf0e6e9c1cb905fccc"

inherit autotools binconfig

#EXTRA_OECONF_armv7a = "-host=armv7-none-linux-gnueabi"

FILES_${PN}-dbg += "/usr/lib/cmake/xapian"