from rest_framework import serializers
from .models import Transportation


class TransportationSerializer(serializers.ModelSerializer):

    class Meta:
        model = Transportation
        fields = '__all__'
