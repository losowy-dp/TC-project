from django.db import models
from django.contrib.auth.models import User


class Car(models.Model):
    manufacture = models.CharField(max_length=150, verbose_name='Producent')
    model = models.CharField(max_length=150, verbose_name='Model')
    nr_car = models.CharField(max_length=150, verbose_name='Numer samochodu')

    def __str__(self):
        return self.manufacture + ' ' + self.model

    class Meta:
        verbose_name = 'Samochud'
        verbose_name_plural = 'Samochody'


class Profile(models.Model):
    user = models.OneToOneField(User, on_delete=models.CASCADE, related_name='profile')
    photo = models.ImageField(upload_to='photos/%y/%m/%d/', verbose_name='Foto', blank=True)
    number_of_phone = models.CharField(max_length=150, verbose_name='Numer telefonu', blank=True, null=True, unique=True)
    birthday = models.DateField(verbose_name='Dzien narodzin', blank=True, null=True)
    driver = models.OneToOneField('Driver', on_delete=models.CASCADE, null=True)

    def __str__(self):
        return self.user.username

    class Meta:
        verbose_name = 'Profil'
        verbose_name_plural = 'Profile'


class Driver(models.Model):
    nr_prawa_jazdy = models.CharField(max_length=200, blank=True)
    ocena = models.FloatField(blank=True, null=True)
    car = models.ForeignKey(Car, models.SET_NULL, blank=True, null=True)

    def __str__(self):
        return self.nr_prawa_jazdy

    class Meta:
        verbose_name = 'Kierowca'
        verbose_name_plural = 'Kierowcy'

