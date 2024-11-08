package com.delivery.tecnokargo.shipipin_product.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.delivery.tecnokargo.mockdata.mockProducts
import com.delivery.tecnokargo.mockdata.mockTravelRoutes
import com.delivery.tecnokargo.shipipin_guide.presentation.viewdata.Product
import com.delivery.tecnokargo.shipipin_rute.presentation.model.RequestProductsEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ShippingProductViewModel
@Inject constructor(
   // private val getGuideRuteUseCase: GetGuideRuteUseCase,
) : ViewModel(){
    private val _productsFilter: MutableStateFlow<List<Product>> =
        MutableStateFlow(emptyList())
    val productsFilter: StateFlow<List<Product>>
        get() = _productsFilter.asStateFlow()



    fun getProducts(id:String, type:RequestProductsEnum){
        when(type){
            RequestProductsEnum.GUIDE_ROUTE -> {
                val travelRoutes = mockTravelRoutes().filter { it.guideRouteId == id }
                _productsFilter.value = mockProducts().filter { product ->
                    travelRoutes.any{
                        it.id == product.travelRouteId
                    }
                }
            }

            RequestProductsEnum.TRAVEL_ROUTE -> {
                _productsFilter.value = mockProducts().filter { it.travelRouteId == id }
            }
        }
    }

}