DESCRIPTION = "anvl-node-xapian"
HOMEPAGE = "https://github.com/networkimprov/node-xapian"
LICENSE = "GPLv2"
# XXX This package is missing a license file
LIC_FILES_CHKSUM = "file://README.txt;md5=45204830c8c87b5a4c4c04386b3222ae"

DEPENDS = "anvl-nodejs2-native xapian-core-dev xapian-core anvl-xapian-omega-dev \
	   anvl-xapian-omega-staticdev"

PR = "r0"

S = "${WORKDIR}/git"

SRCREV = "fbdc81e1d48edca95a430a8986969887bf5bbb20"
SRC_URI = "git://github.com/networkimprov/node-xapian;protocol=https \
	   file://libmime2text-path.patch"

export NODE_PATH = "${D}/usr/lib/nodelib/${PN}"
export PREFIX_NODE = "${D}/opt/node/0.2.6"

do_configure () {
	rm -f ${S}/libmime2text.a
	mkdir -p ${NODE_PATH}
	node-waf -vv configure 
#  echo ${WORKDIR}
	echo 'CPPPATH = "${STAGING_DIR}/${MACHINE}/opt/node/0.2.6/include/node"' >> ${S}/build/c4che/default.cache.py
}

do_install () {
#  echo $PREFIX_NODE
	node-waf -vv install 
}

FILES_${PN} += "/usr/lib/nodelib/${PN}"

RDEPENDS_${PN} = "anvl-nodejs2 xapian-core"
