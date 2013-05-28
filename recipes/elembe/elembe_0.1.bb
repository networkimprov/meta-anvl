DESCRIPTION = "elembe"
HOMEPAGE = "https://github.com/networkimprov/elembe"
LICENSE = "AGPL-3.0"
LIC_FILES_CHKSUM = "file://README;md5=75083f74de6764b70dca50a0a32bc1cd"

DEPENDS = "anvl-nodejs2"

PR = "r0"

S = "${WORKDIR}/git"

SRCREV = "32a46d45ee56a35a58ecb30c2c7d34f678a31ab4"
SRC_URI = "git://github.com/networkimprov/elembe;protocol=git"

do_install () {
  mkdir -p ${D}/home/elembe/lib
  cp -a ${S}/nodelib ${D}/home/elembe/lib
  cp -a ${S}/socket.io ${D}/home/elembe/lib
  cp -a ${S}/client ${D}/home/elembe/lib
  cp -a ${S}/applaunch ${D}/home/elembe/lib
}

pkg_postinst_${PN} () {
        if [ "x$D" != "x" ]; then
                exit 1
        fi

        fgrep elembe /etc/passwd || adduser --disabled-password --home=/home/elembe --group elembe elembe

        chown -R elembe.elembe /home/elembe
}

pkg_postrm_${PN} () {
        grep ${PN} /etc/passwd && deluser ${PN}
}

FILES_${PN} += "/home/elembe/lib"
