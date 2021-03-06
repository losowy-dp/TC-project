# Generated by Django 3.1.7 on 2021-03-16 09:15

from django.conf import settings
from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    initial = True

    dependencies = [
        migrations.swappable_dependency(settings.AUTH_USER_MODEL),
    ]

    operations = [
        migrations.CreateModel(
            name='Car',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('manufacture', models.CharField(max_length=150)),
                ('model', models.CharField(max_length=150)),
                ('nr_car', models.CharField(max_length=150)),
            ],
        ),
        migrations.CreateModel(
            name='Profile',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('photo', models.ImageField(blank=True, upload_to='')),
                ('is_driver', models.BooleanField(default=False)),
                ('nr_prawa_jazdy', models.CharField(blank=True, max_length=200)),
                ('ocena', models.FloatField(blank=True)),
                ('car', models.ForeignKey(blank=True, on_delete=django.db.models.deletion.PROTECT, to='account.car')),
                ('user', models.OneToOneField(on_delete=django.db.models.deletion.CASCADE, to=settings.AUTH_USER_MODEL)),
            ],
        ),
    ]
