SUMMARY = "Rust compiler run-time"
HOMEPAGE = "http://www.rust-lang.org"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/compiler-rt/LICENSE.TXT;md5=bf24bca27049b52e9738451aa55771d4"

SRC_URI = "\
	https://static.rust-lang.org/dist/rustc-${PV}-src.tar.gz;name=rust \
    "

require rust-source-${PV}.inc

S = "${WORKDIR}/rustc-${PV}"

# can't call configure with this flag
EXTRA_OECONF_remove = "--disable-static"

# Pick up $CC from the environment
EXTRA_OEMAKE += "-e"

do_configure() {
    ${S}/configure \
        --enable-rpath \
        --disable-docs \
        --disable-manage-submodules \
        --disable-debug \
        --enable-optimize \
        --disable-llvm-version-check \
        --llvm-root=${STAGING_DIR_NATIVE}/${prefix_native} \
        --release-channel=stable \
        --prefix=${prefix}
        ${EXTRA_OECONF}
}

do_compile () {
	oe_runmake \
		ProjSrcRoot="${S}" \
		ProjObjRoot="${B}" \
		TargetTriple=${HOST_SYS} \
		x86_64-unknown-linux-gnu/rt/libcompiler-rt.a
}

do_install () {
	mkdir -p ${D}${libdir}
	cp triple/builtins/libcompiler_rt.a ${D}${libdir}/libcompiler-rt.a
}
