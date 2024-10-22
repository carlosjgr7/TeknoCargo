package com.delivery.tecnokargo.mockdata

import com.delivery.tecnokargo.shipipin_guide.presentation.viewdata.GuideRoute
import com.delivery.tecnokargo.shipipin_guide.presentation.viewdata.Product
import com.delivery.tecnokargo.shipipin_guide.presentation.viewdata.TravelRoute

fun mockProducts(): List<Product> {
    return listOf(
        Product("P001", "Caja de Productos A", false,"Descripción del Producto A","TR001"),
        Product("P002", "Caja de Productos B",false, "Descripción del Producto B","TR001"),
        Product("P003", "Caja de Productos C", true,"Descripción del Producto C","TR001"),
        Product("P003", "Caja de Productos C", true,"Descripción del Producto C","TR001"),
        Product("P003", "Caja de Productos C", true,"Descripción del Producto C","TR001"),
        Product("P003", "Caja de Productos C", true,"Descripción del Producto C","TR001"),
        Product("P003", "Caja de Productos C", false,"Descripción del Producto C","TR001"),
        Product("P003", "Caja de Productos C", true,"Descripción del Producto C","TR001"),
        Product("P003", "Caja de Productos C", true,"Descripción del Producto C","TR001"),
        Product("P003", "Caja de Productos C", false,"Descripción del Producto C","TR001"),
        Product("P003", "Caja de Productos C", true,"Descripción del Producto C","TR001"),
        Product("P003", "Caja de Productos C", false,"Descripción del Producto C","TR001"),
        Product("P003", "Caja de Productos C", false,"Descripción del Producto C","TR001"),
        Product("P003", "Caja de Productos C", true,"Descripción del Producto C","TR001"),
        Product("P003", "Caja de Productos C", true,"Descripción del Producto C","TR001"),
        Product("P003", "Caja de Productos C", true,"Descripción del Producto C","TR001"),
        Product("P003", "Caja de Productos C", true,"Descripción del Producto C","TR001"),
        Product("P004", "Caja de Productos D", true,"Descripción del Producto D","TR002"),
        Product("P005", "Caja de Productos E", true,"Descripción del Producto E","TR003"),
        Product("P006", "Caja de Productos F", false,"Descripción del Producto F","TR004"),
        Product("P007", "Caja de Productos G", true,"Descripción del Producto G","TR005"),
        Product("P008", "Caja de Productos H", false,"Descripción del Producto H","TR006"),
        Product("P009", "Caja de Productos I", false,"Descripción del Producto I","TR007"),
        Product("P010", "Caja de Productos J", false,"Descripción del Producto J","TR008"),

    )
}

fun mockTravelRoutes(): List<TravelRoute>{
    return listOf(
        TravelRoute("TR001", "1600 Pennsylvania Avenue NW, Washington, DC 20500, USA", "G001",false),
        TravelRoute("TR002", "Champ de Mars, 5 Avenue Anatole France, 75007 Paris, France", "G001",true),
        TravelRoute("TR003", "Liberty Island, New York, NY 10004, USA", "G001",true ),
        TravelRoute("TR004", "London SW1A 1AA, United Kingdom", "G002",false),
        TravelRoute("TR005", "1600 Pennsylvania Avenue NW, Washington, DC 20500, USA", "G003",true),
        TravelRoute("TR005", "1600 Pennsylvania Avenue NW, Washington, DC 20500, USA", "G003",true),
        TravelRoute("TR005", "1600 Pennsylvania Avenue NW, Washington, DC 20500, USA", "G003",true),
        TravelRoute("TR005", "1600 Pennsylvania Avenue NW, Washington, DC 20500, USA", "G003",true),
        TravelRoute("TR005", "1600 Pennsylvania Avenue NW, Washington, DC 20500, USA", "G003",true),
        TravelRoute("TR006", "Champ de Mars, 5 Avenue Anatole France, 75007 Paris, France", "G004",false),
        TravelRoute("TR007", "Liberty Island, New York, NY 10004, USA", "G005",false),
        TravelRoute("TR008", "London SW1A 1AA, United Kingdom", "G006",false),
        TravelRoute("TR009", "1600 Pennsylvania Avenue NW, Washington, DC 20500, USA", "G007",false),

    )
}

fun mockGuideRoutes(): List<GuideRoute>{
    return listOf(
        GuideRoute("G001",  "Descripción del Viaje A"),
        GuideRoute("G002",  "Descripción del Viaje B"),
        GuideRoute("G003",  "Descripción del Viaje C"),
        GuideRoute("G004",  "Descripción del Viaje D"),
        GuideRoute("G005",  "Descripción del Viaje E"),
        GuideRoute("G006",  "Descripción del Viaje F"),
        GuideRoute("G007",  "Descripción del Viaje G"),
    )
}