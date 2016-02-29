SUMMARY = "Open source MQTT v3.1 implemention"
DESCRIPTION = "Mosquitto is an open source (BSD licensed) message broker that implements the MQ Telemetry Transport protocol version 3.1. MQTT provides a lightweight method of carrying out messaging using a publish/subscribe model. "
HOMEPAGE = "http://mosquitto.org/"
SECTION = "console/network"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=62ddc846179e908dc0c8efec4a42ef20"

DEPENDS = "openssl c-ares util-linux"

PR = "r0"

SRC_URI = "http://mosquitto.org/files/source/mosquitto-${PV}.tar.gz \
           file://mosquitto.service \
           file://mosquitto.init \
           file://0001-OpenEmbedded-build-fix.patch \
"

SRC_URI[md5sum] = "d859cd474ffa61a6197bdabe007b9027"
SRC_URI[sha256sum] = "d96eb5610e57cc3e273f4527d3f54358ab7711459941a9e64bc4d0a85c2acfda"


do_install() {
    oe_runmake install DESTDIR=${D}
    install -d ${D}${libbir}
    install -m 0644 lib/libmosquitto.a ${D}${libdir}/

    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/mosquitto.service ${D}${systemd_unitdir}/system/

    install -d ${D}${sysconfdir}/init.d/
    install -m 755 ${WORKDIR}/${PN}.init ${D}${sysconfdir}/init.d/${PN}.init


    cp ${D}${sysconfdir}/mosquitto/mosquitto.conf.example ${D}${sysconfdir}/mosquitto/mosquitto.conf
    sed 's/#user mosquitto/user root/' -i ${D}${sysconfdir}/mosquitto/mosquitto.conf
}

PACKAGES += "libmosquitto1 libmosquittopp1 ${PN}-clients ${PN}-python"

FILES_${PN} = "${sbindir}/mosquitto \
               ${bindir}/mosquitto_passwd \
               ${sysconfdir} \
               ${systemd_unitdir}/system/mosquitto.service \
"

FILES_libmosquitto1 = "${libdir}/libmosquitto.so.1"

FILES_libmosquittopp1 = "${libdir}/libmosquittopp.so.1"

FILES_${PN}-clients = "${bindir}/mosquitto_pub \
                       ${bindir}/mosquitto_sub \
"

FILES_${PN}-staticdev += "${libdir}/libmosquitto.a"

FILES_${PN}-python = "/usr/lib/python2.7/site-packages"

inherit systemd update-rc.d

INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME_${PN} = "${PN}"
INITSCRIPT_PARAMS_${PN} = "defaults 80"


SYSTEMD_SERVICE_${PN} = "mosquitto.service"
