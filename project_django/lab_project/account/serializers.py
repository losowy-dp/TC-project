from rest_framework import serializers
from .models import Profile
from django.contrib.auth.models import User


class UserSer(serializers.ModelSerializer):
    profile = serializers.PrimaryKeyRelatedField(many=False, queryset=Profile.objects.all())

    class Meta:
        model = User
        # fields = '__all__'
        fields = ['id', 'first_name', 'last_name', 'email', 'profile']


class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        # fields = '__all__'
        fields = ['id', 'first_name', 'last_name', 'email']

    def update(self, instance, validated_data):
        instance.first_name = validated_data.get('first_name', instance.first_name)
        instance.last_name = validated_data.get('last_name', instance.last_name)
        instance.email = validated_data.get('email', instance.email)
        instance.save()
        return instance

    # def create(self, validated_data):
    #     """
    #     Create and return a new `Snippet` instance, given the validated data.
    #     """
    #     return User.objects.create(**validated_data)
    #
    # def update(self, instance, validated_data):
    #     """
    #     Update and return an existing `Snippet` instance, given the validated data.
    #     """
    #     instance.title = validated_data.get('title', instance.title)
    #     instance.code = validated_data.get('code', instance.code)
    #     instance.linenos = validated_data.get('linenos', instance.linenos)
    #     instance.language = validated_data.get('language', instance.language)
    #     instance.style = validated_data.get('style', instance.style)
    #     instance.save()
    #     return instance


class ProfileSerializer(serializers.ModelSerializer):

    class Meta:
        model = Profile
        fields = ['id', 'number_of_phone']


    def update(self, instance, validated_data):
        instance.number_of_phone = validated_data.get('number_of_phone', instance.number_of_phone)
        instance.save()
        return instance


class ProfileSer(serializers.ModelSerializer):

    class Meta:
        model = Profile
        fields = ['id', 'number_of_phone']

    def create(self, validated_data):
        return Profile.objects.create(**validated_data)

    def update(self, instance, validated_data):
        instance.number_of_phone = validated_data.get('number_of_phone', instance.number_of_phone)
        return instance
