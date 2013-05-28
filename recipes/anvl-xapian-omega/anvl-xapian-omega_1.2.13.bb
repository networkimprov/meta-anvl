DESCRIPTION = "Omega, a Xapian web search engine application"
HOMEPAGE = "http://xapian.org/"
BUGTRACKER = "http://trac.xapian.org/"
LICENSE = "GPLv2 & GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=4325afd396febcb659c36b49533135d4"

DEPENDS = "xapian-core-dev xapian-core libpcre"
PROVIDES = "anvl-xapian-omega-staticdev anvl-xapian-omega-dev"

PR = "r0"

SRC_URI = "http://oligarchy.co.uk/xapian/${PV}/xapian-omega-${PV}.tar.gz \
	   file://anvl_mime2text-lib.${PV}.patch"

SRC_URI[md5sum] = "7a6985a7b9b1b3f4346cc0a15856faba"
SRC_URI[sha256sum] = "ae35e37f3fddf1330004f41531b9c8be447c95f9bb8b2f135541a78c2c816d34"

inherit autotools binconfig

S = "${WORKDIR}/xapian-omega-${PV}"

# XXX This should be patched into the makefile instead.
# XXX And this whole recipe should be renamed to reflect that it is building
#     and installing the Omega/Anvl libmime2text, not Omega itself.
do_install () {
	mkdir -p ${D}${libdir}
	ar rcs ${D}${libdir}/libmime2text.a htmlparse.o loadfile.o md5.o md5wrap.o \
		metaxmlparse.o mime2text.o myhtmlparse.o pkglibbindir.o \
		runfilter.o svgparse.o tmpdir.o utf8convert.o xmlparse.o \
		xpsxmlparse.o
}

FILES_${PV} = "${libdir}/libmime2text.a"
