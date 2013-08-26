require samba4.inc
# require samba4-basic.inc
PROVIDES = "samba4"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

PR = "r0"

SRC_URI[md5sum] = "db9dc684f167a602c18acc19d3e7e401"
SRC_URI[sha256sum] = "63e53dc270d13231bed56b816c18793d8425f199b1a5efdaadc080f4d6911de3"

# EXTRA_OECONF += "\
# 	ac_cv_path_PYTHON=/not/exist \
# 	ac_cv_path_PYTHON_CONFIG=/not/exist \
# 	SMB_BUILD_CC_NEGATIVE_ENUM_VALUES=yes \
# 	samba_cv_CC_NEGATIVE_ENUM_VALUES=yes \
# 	linux_getgrouplist_ok=no \
# 	samba_cv_HAVE_BROKEN_GETGROUPS=no \
# 	samba_cv_HAVE_FTRUNCATE_EXTEND=yes \
# 	samba_cv_have_setresuid=yes \
# 	samba_cv_have_setresgid=yes \
# 	samba_cv_HAVE_WRFILE_KEYTAB=yes \
# 	samba_cv_linux_getgrouplist_ok=yes \
# 	"

# do_compile () {
# 	base_do_compile
# }
