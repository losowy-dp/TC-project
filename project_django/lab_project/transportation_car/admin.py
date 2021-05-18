from django.contrib import admin
from .models import Transportation, Location, TypeCar

# Register your models here.


class LocationAdmin(admin.ModelAdmin):
    list_display = ('id', 'country', 'regions', 'city', 'address')
    list_display_links = ('id', 'address')


class TransportationAdmin(admin.ModelAdmin):
    list_display = ('id', 'data_created', 'start_location', 'delivery_location', 'price', 'currency')


class TypeCarAdmin(admin.ModelAdmin):
    list_display = ('id', 'typeCar')


# class TransportationCarAdmin(admin.ModelAdmin):
#     list_display = ('id', 'data_created', 'start_location', 'delivery_location', 'model', 'data_start_deliveri', 'data_end_deliveri', 'data_start_shipment', 'data_end_shipment')


admin.site.register(Location, LocationAdmin)
admin.site.register(Transportation, TransportationAdmin)
# admin.site.register(TransportationCar, TransportationCarAdmin)
admin.site.register(TypeCar, TypeCarAdmin)
