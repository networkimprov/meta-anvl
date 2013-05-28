DESCRIPTION = "uuidjs"
HOMEPAGE = "https://bitbucket.org/nikhilm/uuidjs"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8469f9413a19825e48e0d29846388e9c"

DEPENDS = "anvl-nodejs2-native ossp-uuid"

PR = "r0"

S = "${WORKDIR}/uuidjs"

SRCREV = "4:50f00db8b761"
SRC_URI = "hg://bitbucket.org/nikhilm/uuidjs;protocol=https;module=uuidjs"

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

# RDEPENDS_${PN} = "anvl-nodejs2"
