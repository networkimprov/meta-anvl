DESCRIPTION = "anvl-node-sqlite"
HOMEPAGE = "https://github.com/networkimprov/node-sqlite"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://README.md;md5=d076f6ad149cfabcd47bfd3783f74890"

DEPENDS = "anvl-nodejs2-native sqlite3"
# libsqlite3-dev"

PR = "r0"

S = "${WORKDIR}/git"

SRCREV = "5dfe158569113e2963e7122e656db723ea12b08f"
SRC_URI = "git://github.com/networkimprov/node-sqlite;protocol=https"

inherit pkgconfig

export NODE_PATH = "${D}/usr/lib/nodelib/${PN}"
export PREFIX_NODE = "${D}/opt/node/0.2.6"

do_configure () {
	mkdir -p ${NODE_PATH}
	node-waf -vv configure 
	echo 'CPPPATH = "${STAGING_DIR}/${MACHINE}/opt/node/0.2.6/include/node"' >> ${S}/build/c4che/default.cache.py
}

do_install () {
	node-waf -vv install 
}

FILES_${PN} += "/usr/lib/nodelib/${PN}"

RDEPENDS_${PN} = "anvl-nodejs2 sqlite3"
