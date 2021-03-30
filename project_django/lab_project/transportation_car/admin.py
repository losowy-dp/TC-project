from django.contrib import admin
from .models import Transportation,Location

# Register your models here.


class LocationAdmin(admin.ModelAdmin):
    list_display = ('id', 'country', 'regions', 'city', 'address')
    list_display_links = ('id', 'address')


class TransportationAdmin(admin.ModelAdmin):
    list_display = ('id', 'data_created', 'start_location', 'delivery_location', 'price', 'currency')


admin.site.register(Location, LocationAdmin)
admin.site.register(Transportation, TransportationAdmin)