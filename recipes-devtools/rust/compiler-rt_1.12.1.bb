SUMMARY = "Rust compiler run-time"
HOMEPAGE = "http://www.rust-lang.org"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.TXT;md5=bf24bca27049b52e9738451aa55771d4"

SRC_URI = "\
	https://static.rust-lang.org/dist/rustc-${PV}-src.tar.gz;name=rust \
    "

require rust-source-${PV}.inc

S = "${WORKDIR}/rustc-${PV}/src/compiler-rt"

# Pick up $CC from the environment
EXTRA_OEMAKE += "-e"

do_compile () {
	oe_runmake -C ${S} \
		ProjSrcRoot="${S}" \
		ProjObjRoot="${B}" \
		TargetTriple=${HOST_SYS} \
		triple-builtins
}

do_install () {
	mkdir -p ${D}${libdir}
	cp triple/builtins/libcompiler_rt.a ${D}${libdir}/libcompiler-rt.a
}
