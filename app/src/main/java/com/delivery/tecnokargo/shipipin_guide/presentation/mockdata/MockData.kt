package com.delivery.tecnokargo.shipipin_guide.presentation.mockdata

import com.delivery.tecnokargo.shipipin_guide.presentation.viewdata.GuideRoute
import com.delivery.tecnokargo.shipipin_guide.presentation.viewdata.Product
import com.delivery.tecnokargo.shipipin_guide.presentation.viewdata.TravelRoute

fun mockProducts(): List<Product> {
    return listOf(
        Product("P001", "Caja de Productos A", "Descripción del Producto A"),
        Product("P002", "Caja de Productos B", "Descripción del Producto B"),
        Product("P003", "Caja de Productos C", "Descripción del Producto C"),
        Product("P004", "Caja de Productos D", "Descripción del Producto D"),
        Product("P005", "Caja de Productos E", "Descripción del Producto E"),
        Product("P006", "Caja de Productos F", "Descripción del Producto F"),
        Product("P007", "Caja de Productos G", "Descripción del Producto G"),
        Product("P008", "Caja de Productos H", "Descripción del Producto H"),
        Product("P009", "Caja de Productos I", "Descripción del Producto I"),
        Product("P010", "Caja de Productos J", "Descripción del Producto J"),

    )
}

fun mockTravelRoutes(): List<TravelRoute>{
    return listOf(
        TravelRoute("TR001", "1600 Pennsylvania Avenue NW, Washington, DC 20500, USA", listOf("P001", "P002")),
        TravelRoute("TR002", "Champ de Mars, 5 Avenue Anatole France, 75007 Paris, France", listOf("P003", "P004")),
        TravelRoute("TR003", "Liberty Island, New York, NY 10004, USA", listOf("P005", "P006")),
        TravelRoute("TR004", "London SW1A 1AA, United Kingdom", listOf("P007", "P008","P009", "P010")),
        TravelRoute("TR005", "1600 Pennsylvania Avenue NW, Washington, DC 20500, USA", listOf("P001", "P002")),
        TravelRoute("TR006", "Champ de Mars, 5 Avenue Anatole France, 75007 Paris, France", listOf("P003", "P004")),
        TravelRoute("TR007", "Liberty Island, New York, NY 10004, USA", listOf("P005", "P006")),

    )
}

fun mockGuideRoutes(): List<GuideRoute>{
    return listOf(
        GuideRoute("G001", listOf("TR001", "TR002,TR004"), "Descripción del Viaje A"),
        GuideRoute("G002", listOf("TR002", "TR003,TR005, TR006, TR007"), "Descripción del Viaje B"),

    )
}