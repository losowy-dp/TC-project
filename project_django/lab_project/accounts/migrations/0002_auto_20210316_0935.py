# Generated by Django 3.1.7 on 2021-03-16 09:35

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('accounts', '0001_initial'),
    ]

    operations = [
        migrations.AlterField(
            model_name='profile',
            name='car',
            field=models.ForeignKey(blank=True, null=True, on_delete=django.db.models.deletion.SET_NULL, to='accounts.car'),
        ),
        migrations.AlterField(
            model_name='profile',
            name='ocena',
            field=models.FloatField(blank=True, null=True),
        ),
    ]
