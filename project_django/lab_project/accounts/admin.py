from django.contrib import admin
from .models import Car, Profile, Driver

# Register your models here.


class ProfileAdmin(admin.ModelAdmin):
    list_display = ('id', 'user', 'number_of_phone', 'birthday', 'driver')
    list_display_links = ('id', 'user')


class DriverAdmin(admin.ModelAdmin):
    list_display = ('id', 'nr_prawa_jazdy', 'ocena', 'car')
    list_display_links = ('id', 'nr_prawa_jazdy')


class CarAdmin(admin.ModelAdmin):
    list_display = ('id', 'manufacture', 'model', 'nr_car')
    list_display_links = ('id', )


admin.site.register(Car, CarAdmin)
admin.site.register(Profile, ProfileAdmin)
admin.site.register(Driver, DriverAdmin)
