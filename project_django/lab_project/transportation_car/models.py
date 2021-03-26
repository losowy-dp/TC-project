from django.db import models
from django.contrib.auth.models import User


class Location(models.Model):
    country = models.CharField(max_length=150, verbose_name='Panstwo')
    regions = models.CharField(max_length=150, verbose_name='Region')
    city = models.CharField(max_length=150, verbose_name='Miasto')
    address = models.CharField(max_length=300, verbose_name='Adres')

    def __str__(self):
        return self.city + ', ' + self.address

    class Meta:
        verbose_name = 'Lokalizacja'
        verbose_name_plural = 'Lokalizacje'


class Transportation(models.Model):
    car_owner = models.ForeignKey(User, on_delete=models.CASCADE, related_name='car_owner')
    data_created = models.DateTimeField(auto_now_add=True, verbose_name='Data publikacji')
    start_location = models.ForeignKey('Location', on_delete=models.PROTECT, related_name='start_location')
    description = models.CharField(max_length=1000, verbose_name='Opis', blank=True)
    delivery_location = models.ForeignKey('Location', on_delete=models.PROTECT, related_name='delivery_location')
    delivery_date = models.DateTimeField(verbose_name='Data dostawy')
    price = models.FloatField(verbose_name='Cena')
    is_free = models.BooleanField(default=True, verbose_name='Czy dostempny')
    is_delivery = models.BooleanField(default=False, verbose_name='Czy dostarczony')
    driver = models.ForeignKey(User, on_delete=models.PROTECT, verbose_name='kierowca', related_name='driver', blank=True, null=True)

    def __str__(self):
        return str(self.start_location) + ' - ' + str(self.delivery_location)

    class Meta:
        verbose_name = 'Transportacja'
        verbose_name_plural = 'Transportacji'