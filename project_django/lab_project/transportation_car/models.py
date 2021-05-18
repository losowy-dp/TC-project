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


class TypeCar(models.Model):
    typeCar = models.CharField(max_length=100, verbose_name='Data publikacji')

    def __str__(self):
        return self.typeCar

    class Meta:
        verbose_name = 'Rodzai samochoduw'
        verbose_name_plural = 'Rodzaj samochodu'


class Transportation(models.Model):
    car_owner = models.ForeignKey(User, on_delete=models.CASCADE, related_name='car_owner', default=None)
    data_created = models.DateTimeField(auto_now_add=True, verbose_name='Data publikacji')
    # start_location = models.ForeignKey('Location', on_delete=models.PROTECT, related_name='start_location')
    start_location = models.CharField(max_length=1000)
    description = models.CharField(max_length=1000, verbose_name='Opis', blank=True)
    # delivery_location = models.ForeignKey('Location', on_delete=models.PROTECT, related_name='delivery_location')
    model = models.CharField(max_length=1000, verbose_name='Marka i model', blank=True)
    typeCar = models.ForeignKey(TypeCar, on_delete=models.PROTECT, related_name='car', blank=True, null=True, verbose_name='Rodzaj samochodu')
    delivery_location = models.CharField(max_length=1000)
    price = models.FloatField(verbose_name='Cena')
    currency = models.CharField(max_length=3, verbose_name='Waluta', default=None, )
    data_start_deliveri = models.DateField(verbose_name='Poczotek terminu dostawy', blank=True, null=True)
    data_end_deliveri = models.DateField(verbose_name='Koniec terminu dostawy', blank=True, null=True)
    data_start_shipment = models.DateField(verbose_name='Poczotek terminu nadania', blank=True, null=True)
    data_end_shipment = models.DateField(verbose_name='Koniec terminu nadania', blank=True, null=True)

    photo = models.ImageField(upload_to='images_car/%y/%m/%d/', verbose_name='Image', blank=True)
    punktA_1 = models.CharField(verbose_name='Koordunata A_1', max_length=30, blank=True)
    punktA_2 = models.CharField(verbose_name='Koordunata A_2', max_length=30, blank=True)
    punktB_1 = models.CharField(verbose_name='Koordunata B_1', max_length=30, blank=True)
    punktB_2 = models.CharField(verbose_name='Koordunata B_2', max_length=30, blank=True)

    def __str__(self):
        return str(self.start_location) + ' - ' + str(self.delivery_location)

    class Meta:
        verbose_name = 'Transportacja'
        verbose_name_plural = 'Transportacji'


# class TransportationCar(models.Model):
#     car_owner = models.ForeignKey(User, on_delete=models.CASCADE, related_name='owner')
#     data_created = models.DateTimeField(auto_now_add=True, verbose_name='Data publikacji')
#     start_location = models.CharField(max_length=1000)
#     delivery_location = models.CharField(max_length=1000)
#     model = models.CharField(max_length=1000, verbose_name="Model i marks")
#     data_start_deliveri = models.DateTimeField(verbose_name='Poczotek terminu dostawy', blank=True)
#     data_end_deliveri = models.DateTimeField(verbose_name='Koniec terminu dostawy', blank=True)
#     data_start_shipment = models.DateTimeField(verbose_name='Poczotek terminu nadania', blank=True)
#     data_end_shipment = models.DateTimeField(verbose_name='Koniec terminu nadania', blank=True)
#
#     def __str__(self):
#         return str(self.start_location) + ' - ' + str(self.delivery_location)
#
#     class Meta:
#         verbose_name = 'Transportation'
#         verbose_name_plural = 'Transportations'



# class Order(models.Model):
#     transportations = models.ForeignKey(Transportation, on_delete=models.PROTECT())
#     driver = models.ForeignKey(User, on_delete=models.PROTECT, verbose_name='kierowca', related_name='driver',
#                                blank=True, null=True)



