# Generated by Django 3.1.7 on 2021-05-15 13:54

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('transportation_car', '0015_auto_20210515_1416'),
    ]

    operations = [
        migrations.AddField(
            model_name='transportation',
            name='punktA',
            field=models.CharField(blank=True, max_length=30, verbose_name='Koordunata A'),
        ),
        migrations.AddField(
            model_name='transportation',
            name='punktB',
            field=models.CharField(blank=True, max_length=30, verbose_name='Koordunata B'),
        ),
    ]
