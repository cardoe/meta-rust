require rust.inc
require rust-source-${PV}.inc

# the configure script always requires cmake so despite
# rust not needing this (only rust-llvm needs it) we must
# have it for the configure script to succeed.
DEPENDS += "cmake-native"

SRC_URI += " \
        file://rust-${PV}/0001-Target-add-default-target.json-path-libdir-rust-targ.patch \
        file://rust-${PV}/0002-mk-for-stage0-use-RUSTFLAGS-to-override-target-libs-.patch \
        file://rust-${PV}/0003-std-thread_local-workaround-for-NULL-__dso_handle.patch \
        "

DEPENDS += "rust-llvm (=${PV})"

# Otherwise we'll depend on what we provide
INHIBIT_DEFAULT_RUST_DEPS_class-native = "1"
# We don't need to depend on gcc-native because yocto assumes it exists
PROVIDES_class-native = "virtual/${TARGET_PREFIX}rust"

BBCLASSEXTEND = "native"
