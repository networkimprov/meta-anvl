require ${COREBASE}/meta/recipes-bsp/u-boot/u-boot.inc

DESCRIPTION = "u-boot bootloader for the ANVL board"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=1707d6db1d42237583f50183a5651ecb"

COMPATIBLE_MACHINE = "(anvl)"

DEFAULT_PREFERENCE = "-1"

PACKAGE_ARCH = "${MACHINE_ARCH}"

PR ="r0+gitr${SRCPV}"

SRC_URI = "git://github.com/networkimprov/u-boot.git;protocol=git;branch=braden/dev"

SRCREV = "9b06ed131b4d77f45e33240bbbea2898baec4575"

S = "${WORKDIR}/git"

UBOOT_SUFFIX = "img"

SPL_BINARY = "MLO"
