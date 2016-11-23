# Copyright (C) 2016 Doug Goldstein <cardoe@cardoe.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "A small image to test a Rust binary on the target"

IMAGE_INSTALL = " \
    packagegroup-core-boot \
    ${ROOTFS_PKGMANAGE_BOOTSTRAP} \
    rustfmt \
    "
IMAGE_LINGUAS = " "

LICENSE = "MIT"

inherit core-image

IMAGE_ROOTFS_SIZE ?= "8192"
