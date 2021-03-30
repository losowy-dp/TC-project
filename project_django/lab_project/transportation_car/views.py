from django.shortcuts import render
from rest_framework import generics

from .models import Transportation, Location
from .serializers import TransportantionSerializer

# Create your views here.


def index(request):
    transportations = Transportation.objects.all()
    context = {
        'transportations': transportations
    }
    return render(request, 'transportation_car/index.html', context)


class getTransportations(generics.ListCreateAPIView):
    queryset = Transportation.objects.all()
    serializer_class = TransportantionSerializer


# def createTransportations(request):
#