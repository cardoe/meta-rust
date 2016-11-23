#!/bin/bash

die() {
    echo "$*" >&2
    exit 1
}

# Grab the MACHINE from the environment; otherwise, set it to a sane default
export MACHINE="${MACHINE-qemux86-64}"

[ $# -ge 2 ] || die "basic-test IMAGE-FILESYSTEM PROGRAM-TO-RUN-INSIDE ARGS-TO-PROG"

imgfs=$1
shift
prog=$1
shift

case "${MACHINE}" in
    qemuarm) qemubin="qemu-arm" ;;
    *) die "Unable to determine qemu binary for ${MACHINE}"
esac

mkdir -p foo
mount -o loop build/tmp/deploy/images/${MACHINE}/${imgfs}-${MACHINE}.ext4 foo/
[ $? -eq 0 ] || die "Failed to mount ${imgfs}"


./build/tmp/sysroots/x86_64-linux/usr/bin/${qemubin} -L foo/ foo/${prog} "$@"
