# Generated by Django 3.1.7 on 2021-05-15 14:07

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('transportation_car', '0016_auto_20210515_1554'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='transportation',
            name='punktA',
        ),
        migrations.RemoveField(
            model_name='transportation',
            name='punktB',
        ),
        migrations.AddField(
            model_name='transportation',
            name='punktA_1',
            field=models.CharField(blank=True, max_length=30, verbose_name='Koordunata A_1'),
        ),
        migrations.AddField(
            model_name='transportation',
            name='punktA_2',
            field=models.CharField(blank=True, max_length=30, verbose_name='Koordunata A_2'),
        ),
        migrations.AddField(
            model_name='transportation',
            name='punktB_1',
            field=models.CharField(blank=True, max_length=30, verbose_name='Koordunata B_1'),
        ),
        migrations.AddField(
            model_name='transportation',
            name='punktB_2',
            field=models.CharField(blank=True, max_length=30, verbose_name='Koordunata B_2'),
        ),
    ]
