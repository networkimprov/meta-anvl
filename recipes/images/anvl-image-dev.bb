#Angstrom bootstrap image
require console-base-image.bb
inherit core-image

DEPENDS += "packagegroup-base-extended \
	    packagegroup-core-ssh-openssh \
	    apache2 \
	    sqlite \
	    openssl \
	    ntp \
	    anvl-nodejs2 \
	    anvl-nodejs4 \	    
	    nodejs8 \
	    xapian-core \
	    xdelta3 \
	    elembe \
	    anvl-node-inotify \
	    anvl-node-sqlite \
	    anvl-node-xapian \
	    graphicsmagick \
	    uuidjs \
	    bash \
	    perl \
	    binutils \
	    gcc \ 
	    make \
	   "

IMAGE_INSTALL += "packagegroup-base-extended \
	          packagegroup-core-ssh-openssh \
	      	  apache2 \
		  sqlite \
		  openssl \
		  ntp \
		  anvl-nodejs2 \
		  anvl-nodejs4 \
  	    	  nodejs8 \
		  xapian-core \
		  xdelta3 \
		  elembe \
		  anvl-node-inotify \
	    	  anvl-node-sqlite \
	    	  anvl-node-xapian \
		  graphicsmagick \
		  uuidjs \
		  bash \
		  perl \
		  binutils \
		  gcc \
		  make \
		  linux-firmware-sd8787 \
	    "


RDEPENDS_${PN} += "openssh-sshd \
		  openssh-sftp \
		  openssh-sftp-server \
		  "

IMAGE_FEATURES += "package-management ssh-server-openssh"

export IMAGE_BASENAME = "anvl-image-dev"
