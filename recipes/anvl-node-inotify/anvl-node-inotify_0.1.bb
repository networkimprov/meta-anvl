DESCRIPTION = "anvl-node-inotify"
HOMEPAGE = "https://github.com/c4milo/node-inotify"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=edbe272901aca681fd3536b46e4020f4"

DEPENDS = "anvl-nodejs2-native"

PR = "r0"

S = "${WORKDIR}/git"

# XXX arbitrary commit, picked to try to find something that builds for node 0.2
SRCREV = "351983aae3dda6832de6acb2376113ebde94a7f1"
SRC_URI = "git://github.com/c4milo/node-inotify;protocol=https"

export NODE_PATH = "${D}/usr/lib/nodelib/${PN}"

do_configure () {
	mkdir -p ${NODE_PATH}
	node-waf -vv configure 
}

do_build () {
	node-waf build
}

do_install () {
	node-waf install 
}

FILES_${PN} += "/usr/lib/nodelib/${PN}"

RDEPENDS_${PN} = "anvl-nodejs2"
