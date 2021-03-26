# Generated by Django 3.1.7 on 2021-03-16 18:38

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('accounts', '0003_auto_20210316_1025'),
    ]

    operations = [
        migrations.AlterModelOptions(
            name='car',
            options={'verbose_name': 'Samochud', 'verbose_name_plural': 'Samochody'},
        ),
        migrations.AlterModelOptions(
            name='driver',
            options={'verbose_name': 'Kierowca', 'verbose_name_plural': 'Kierowcy'},
        ),
        migrations.AlterModelOptions(
            name='profile',
            options={'verbose_name': 'Profil', 'verbose_name_plural': 'Profile'},
        ),
        migrations.AlterField(
            model_name='profile',
            name='number_of_phon',
            field=models.CharField(blank=True, max_length=150, null=True, verbose_name='Numer telefonu'),
        ),
    ]
