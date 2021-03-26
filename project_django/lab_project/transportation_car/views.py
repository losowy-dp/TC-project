from django.shortcuts import render
from .models import Transportation, Location

# Create your views here.


def index(request):
    transportations = Transportation.objects.all()
    context = {
        'transportations': transportations
    }
    return render(request, 'transportation_car/index.html', context)

