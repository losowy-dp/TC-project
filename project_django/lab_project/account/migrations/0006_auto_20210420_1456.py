# Generated by Django 3.1.7 on 2021-04-20 12:56

from django.conf import settings
from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        migrations.swappable_dependency(settings.AUTH_USER_MODEL),
        ('account', '0005_auto_20210316_1857'),
    ]

    operations = [
        migrations.AlterField(
            model_name='profile',
            name='number_of_phone',
            field=models.CharField(blank=True, max_length=150, null=True, unique=True, verbose_name='Numer telefonu'),
        ),
        migrations.AlterField(
            model_name='profile',
            name='user',
            field=models.OneToOneField(on_delete=django.db.models.deletion.CASCADE, related_name='profile', to=settings.AUTH_USER_MODEL),
        ),
    ]
