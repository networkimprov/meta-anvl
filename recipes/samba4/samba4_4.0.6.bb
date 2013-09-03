require samba4.inc
# require samba4-basic.inc
PROVIDES = "samba4"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

PR = "r0"

SRC_URI[md5sum] = "db9dc684f167a602c18acc19d3e7e401"
SRC_URI[sha256sum] = "63e53dc270d13231bed56b816c18793d8425f199b1a5efdaadc080f4d6911de3"

do_compile () {
	base_do_compile
}
