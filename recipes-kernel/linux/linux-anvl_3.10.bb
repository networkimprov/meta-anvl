require recipes-kernel/linux/linux.inc

DESCRIPTION = "Linux kernel for the ANVL board"
KERNEL_IMAGETYPE = "uImage"
# KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"
EXTRA_OEMAKE += "LOADADDR=${UBOOT_ENTRYPOINT} -d"

COMPATIBLE_MACHINE = "(anvl)"

KERNEL_DEVICETREE_anvl = "arch/${ARCH}/boot/dts/omap3-anvl.dts"

PV = "3.10"

FILESPATH =. "${FILE_DIRNAME}/linux-anvl-3.10:${FILE_DIRNAME}/linux-anvl-3.10/${MACHINE}:"

SRC_URI = "git://github.com/networkimprov/linux.git;protocol=git;branch=dev \
	   file://defconfig"

SRCREV_pn-${PN} = "14497d306986a874d1d304e42917b24c22f4e33a"

S = "${WORKDIR}/git"

do_compile_perf() {
	        :
}

do_install_perf() {
	        :
}
