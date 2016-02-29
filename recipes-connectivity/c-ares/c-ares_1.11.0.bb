DESCRIPTION = "C library for asynchronous DNS requests (including name resolves)"
HOMEPAGE = "http://c-ares.haxx.se/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README.md;beginline=21;endline=22;md5=b320556568bc067d215a1e34c5b34a14"

PR = "r0"

SRC_URI = "http://c-ares.haxx.se/download/c-ares-${PV}.tar.gz"

SRC_URI[md5sum] = "d5c6d522cfc54bb6f215a0b7912d46be"
SRC_URI[sha256sum] = "b3612e6617d9682928a1d50c1040de4db6519f977f0b25d40cf1b632900b3efd"

inherit autotools
