LIC_FILES_CHKSUM = "file://LICENCE.Marvell;md5=9ddea1734a4baf3c78d845151f42a37a"

SRCREV = "931e4469dc254df66a2c990ff1a8723685759eb4"

do_install_append() {
	install -m 0644 LICENCE.Marvell ${D}/lib/firmware
	install -m 0644 mrvl/sd8787_uapsta.bin ${D}/lib/firmware/mrvl
}

PACKAGES = "${PN}-sd8787"

FILES_${PN}-sd8787 = " \
  /lib/firmware/mrvl/sd8787_uapsta.bin \
  /lib/firmware/LICENCE.Marvell \
"
