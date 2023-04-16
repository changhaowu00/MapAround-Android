package com.example.maparound.ui.screens.home

import com.example.maparound.domain.model.Place

object PlaceMock {
    var places = listOf<Place>(
        Place(
            id = "0",
            image_url = "https://github.com/changhaowu00/ArModels/raw/main/ImagesTFG/uc3m_mapAround.jpg",
            icon_url = "https://github.com/changhaowu00/ArModels/raw/main/ImagesTFG/uc3m.png",
            user_name = "UC3M",
            title = "Defensa TFG Map Around",
            tag = "Evento",
            distance = "1m",
            price = "Gratis",
            date_time = "12/04/23, 12:00",
            publish_time = "23d"
        ),
        Place(
            id = "1",
            image_url = "https://github.com/changhaowu00/ArModels/raw/main/ImagesTFG/hakanton.webp",
            icon_url = "https://github.com/changhaowu00/ArModels/raw/main/ImagesTFG/uc3m.png",
            user_name = "UC3M",
            title = "Hackathon UC3M",
            tag = "Evento",
            distance = "12m",
            price = "Solo Estudiantes",
            date_time = "12/04/23, 13:00",
            publish_time = "26d"
        ),
        Place(
            id = "2",
            image_url = "https://github.com/changhaowu00/ArModels/raw/main/ImagesTFG/dia-photo.jpg",
            icon_url = "https://github.com/changhaowu00/ArModels/raw/main/ImagesTFG/dia.jpg",
            user_name = "Día",
            title = "Día",
            tag = "Supermercado",
            distance = "25m",
            price = null,
            date_time = "8:00-21:00"
        ),
        Place(
            id = "3",
            image_url = "https://github.com/changhaowu00/ArModels/raw/main/ImagesTFG/pista-baloncesto.jpg",
            icon_url = "https://github.com/changhaowu00/ArModels/raw/main/ImagesTFG/baloncesto-icon.webp",
            user_name = "El triple tiro",
            title = "Quedada de Baloncesto parque Leganés",
            tag = "Evento",
            distance = "27m",
            price = "Gratis",
            date_time = "12/04/23, 13:00",
            publish_time = "14h"
        ),
        Place(
            id = "4",
            image_url = "https://github.com/changhaowu00/ArModels/raw/main/ImagesTFG/torneo-ajedrez.jpg",
            icon_url = "https://raw.githubusercontent.com/changhaowu00/ArModels/main/ImagesTFG/ajedrez-icon.webp",
            user_name = "Paloma",
            title = "Torneo Ajedrez ",
            tag = "Evento",
            distance = "29m",
            price = "2€/Entrada",
            date_time = "12/04/23, 13:00",
            publish_time = "2d"
        ),
        Place(
            id = "5",
            image_url = "https://github.com/changhaowu00/ArModels/raw/main/ImagesTFG/bar-salamanca.jpg",
            icon_url = "https://media.timeout.com/images/101415021/image.jpg",
            user_name = "Bar Salamanca de Leganes",
            title = "Bar Salamanca de Leganes",
            tag = "Bar",
            distance = "31m",
            price = null,
            date_time = "8:00-21:00"
        ),
        Place(
            id = "6",
            image_url = "https://github.com/changhaowu00/ArModels/raw/main/ImagesTFG/bus.jpg",
            icon_url = "https://github.com/changhaowu00/ArModels/raw/main/ImagesTFG/bus-ico.jpg",
            user_name = "Julio",
            title = "Cuidado que la Línea de bus 460 no está disponible , no han avisado y me acabo de enterar :) ",
            tag = "Momento",
            distance = "33m",
            price = null,
            publish_time = "12m"
        ),
    )
}