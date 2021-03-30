from django.urls import path

from .views import *

urlpatterns = [
    path('', index, name='home'),
    path('getTransportions/', getTransportations.as_view(), name='getTransportantions'),
    # path('createTransportations/', createTransportations, name='createTransportations'),
]