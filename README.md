meta-anvl
=========

Build on top of the angstrom-v2012.12-yocto1.3 branch.
The image name to build with bitbake is 'anvl-image'.

1. Make a directory
   mkdir anvl; cd anvl

2. Clone Ångström setup
   git clone git://github.com/Angstrom-distribution/setup-scripts.git

3. Clone and configure Ångström/OE
   cd setup-scripts
   MACHINE=beagleboard ./oebb.sh config beagleboard

4. Switch to angstrom-v2012.12-yocto1.3
   git checkout remotes/origin/angstrom-v2012.12-yocto1.3

5. Update Ångström/OE
   MACHINE=beagleboard ./oebb.sh update

6. Edit your local.conf if you'd like to change the build parameters.
   Be sure to add 'MACHINE = "anvl"' to local.conf.
   emacs conf/local.conf
   .. then add:
   MACHINE = "anvl"
   .. somewhere in the file along with your other edits

7. Set up the OE build environment variables
   . ~/.oe/environment-angstromv2012.12 

8. Clone the meta-anvl repository
   cd sources
   git clone https://github.com/anvlrootfsdev/meta-anvl.git
   cd ..

9. Add the meta-anvl layer into conf/bblayers.conf into the BASELAYERS variable
   emacs conf/bblayers.conf
   .. then add:
   ${TOPDIR}/sources/meta-anvl \ 
   .. to the BSPLAYERS section

10. Build the image
    bitbake anvl-image 


NOTES:

1) Images are produced in:

	build/tmp-angstrom_v2012_12-eglibc/deploy/images/beagleboard

2) There are no accounts currently configured; one will need to be added, or
   the root account will need to be unlocked.

3) If you encounter an error about argument too long, that's because the path
   of your repository is too long.  Either move the directory to shorten the
   path or you need to add a patch that hacks the kernel's
   'scripts/Makefile.headersinst' file.

4) If you get an error complaining about uuid.h not being present but it
   really is present, just rerun the bitbake command.  I'm guessing this
   is due to a recipe bug in xapian WRT highly parallel builds but I
   haven't investigated.
