inherit rust-installer
require rust.inc
require rust-source-${PV}.inc

SRC_URI += " \
        file://rust-${PV}/0001-Target-add-default-target.json-path-libdir-rust-targ.patch \
        file://rust-${PV}/0002-mk-for-stage0-use-RUSTFLAGS-to-override-target-libs-.patch \
        file://rust-${PV}/0003-mk-add-missing-CFG_LIBDIR_RELATIVE.patch \
        file://rust-${PV}/0004-configure-support-bindir-and-extend-libdir-to-non-bl.patch \
        file://rust-${PV}/0005-std-thread_local-workaround-for-NULL-__dso_handle.patch \
        file://rust-${PV}/0006-mk-install-use-disable-rewrite-paths.patch \
        file://rust-${PV}/0007-Allow-overriding-crate_hash-with-C-crate_hash.patch \
        file://rust-${PV}/0008-mk-platform.mk-pass-C-crate_hash-to-builds.patch \
        file://rust-${PV}/0009-mk-allow-changing-the-platform-configuration-source-.patch \
        file://rust-installer-${PV}/0001-add-option-to-disable-rewriting-of-install-paths.patch;patchdir=src/rust-installer \
        "

BBCLASSEXTEND = "native"
