from datetime import datetime, date, timedelta

from django.db.models.functions import Coalesce
from django.http import Http404
from django.shortcuts import render
from rest_framework.response import Response
from rest_framework import generics
from rest_framework.decorators import api_view
from rest_framework.views import APIView

from .models import Transportation, Location
from .serializers import TransportationSerializer
from .forms import AddTransportations, AddressForm, TransportationForm, FindTransportForm, User, FindTransportationsDataCreatedForm
from account.forms import ProfileForm
from account.models import Profile


def main(request):
    if request.user.is_authenticated:
        profile = Profile.objects.get(user_id=request.user.id)
    else:
        profile = None
    context = {
        'profile': profile,
    }
    return render(request=request, template_name='transportation_car/main.html', context=context)


def index(request, ile):
    if request.user.is_authenticated:
        profile = Profile.objects.get(user_id=request.user.id)
    else:
        profile = None
    form = FindTransportForm()
    # profile = Profile.objects.get()
    if request.method == 'POST':
        form1 = FindTransportForm(request.POST)
        if form1.is_valid():
            cd = form1.cleaned_data
            transportations = Transportation.objects.filter(start_location=cd['start']).filter(delivery_location=cd['end'])
            context = {
                'profile': profile,
                'form': form1,
                'transportations': transportations[10 * (ile - 1):10 * ile],
                'length': transportations.count(),
                'number': ile,
            }
            return render(request=request, template_name='transportation_car/index.html', context=context)
        else:
            print(form1.errors)
            # transportations = Transportation.objects.all().order_by('data_created').desc()
            transportations = Transportation.objects.all().order_by('-data_created')
            print(type(transportations))
            context = {
                'profile': profile,
                'form': form,
                'transportations': transportations[10 * (ile - 1):10 * ile],
                'length': transportations.count(),
                'number': ile,
            }
            return render(request=request, template_name='transportation_car/index.html', context=context)
    else:
        # transportations = Transportation.objects.all().order_by('data_created').desc()
        transportations = Transportation.objects.all().order_by('-data_created')
        print(type(transportations))
        context = {
            'profile': profile,
            'form': form,
            'transportations': transportations[10*(ile-1):10*ile],
            'length': transportations.count(),
            'number': ile,
        }
        return render(request=request, template_name='transportation_car/index.html', context=context)


def transportationId(request, id):
    transportation = Transportation.objects.get(pk=id)
    if request.user.is_authenticated:
        profile = Profile.objects.get(user_id=request.user.id)
        context = {
            'profile': profile,
            'transportation': transportation,
        }
    else:
        context = {
            'transportation': transportation,
        }
    return render(request=request, template_name='transportation_car/transportation.html', context=context)

def getUserOrder(request, user_id, ile, date1,date2):
    # if request.method == 'GET':
    profile = Profile.objects.get(user_id=request.user.id)
    date_1 = datetime.strptime(date1, '%Y-%m-%d')
    date_2 = datetime.strptime(date2, '%Y-%m-%d')
    new_end = date_2 + timedelta(days=1)
    transportations = Transportation.objects.filter(car_owner=user_id, data_created__range=[date_1, new_end]).order_by('-data_created')
    form = FindTransportationsDataCreatedForm()

    context = {
        'profile': profile,
        'transportations': transportations[10*(ile-1):10*ile],
        'length': transportations.count(),
        'number': ile,
        'form': form,
        'date1': str(date_1),
        'date2': str(date_2),
    }
    return render(request=request, template_name='transportation_car/my_orders.html', context=context)

def deleteTransportId(request, id):
    transportation = Transportation.objects.get(pk=id)
    transportation.delete()
    user = User.objects.get(pk=request.user.id)
    profile = Profile.objects.get(user_id=request.user.id)
    form_change_photo = ProfileForm()
    transportations = Transportation.objects.filter(car_owner=request.user.id).order_by('-data_created')
    context = {
    }
    user = User.objects.get(pk=request.user.id)
    print(user.date_joined)
    return getUserOrder(request, request.user.id, 1, user.date_joined.strftime('%Y-%m-%d'), date.today().strftime('%Y-%m-%d'))
    # return render(request, 'transportation_car/main.html', context)

def editTransportId(request, id):
    profile = Profile.objects.get(user_id=request.user.id)
    transportation = Transportation.objects.get(pk=id)
    if request.method == 'POST':
        form = TransportationForm(request.POST)
        if form.is_valid():
            if request.FILES:
                transportation.photo = request.FILES['photo']
            transportation.start_location = form.cleaned_data['start_location']
            transportation.delivery_location = form.cleaned_data['delivery_location']
            transportation.price = form.cleaned_data['price']
            transportation.currency = form.cleaned_data['currency']
            transportation.typeCar = form.cleaned_data['typeCar']
            transportation.model = form.cleaned_data['model']
            transportation.punktA_1 = form.cleaned_data['punktA_1']
            transportation.punktA_2 = form.cleaned_data['punktA_2']
            transportation.punktB_1 = form.cleaned_data['punktB_1']
            transportation.punktB_2 = form.cleaned_data['punktB_2']
            transportation.description = form.cleaned_data['description']
            transportation.save()
            transportations = Transportation.objects.all().order_by('-data_created')
            context = {
                'profile': profile,
                'transportations': transportations
            }
            return render(request=request, template_name='transportation_car/main.html', context=context)
            # return render(request, 'account/register_done.html', {'new_user': "new_user"})
        else:
            print(form.errors)
            return render(request, 'account/invalid_data.html', {'error': 'Invalid data!!!'})
            # return HttpResponse('Invalid login')
    else:
        form = TransportationForm()
        context = {
            'profile': profile,
            'form': form,
            'transportations': transportation,
        }
        return render(request, 'transportation_car/edit_transportations.html', context)


class getTransportations(generics.ListCreateAPIView):
    # queryset = Transportation.objects.all()
    queryset = Transportation.objects.all().order_by('-data_created')
    serializer_class = TransportationSerializer


class getTransportationId(generics.ListCreateAPIView):
    serializer_class = TransportationSerializer

    def get_queryset(self, **kwargs):
        user_id = int(self.kwargs['user_id'])
        # user = User.objects.get(pk=user_id)
        # model_instance = Profile.objects.get_or_create(user=user)
        return Transportation.objects.filter(car_owner=self.kwargs['user_id']).order_by('-data_created')

class getTransportationIdSortOld(generics.ListCreateAPIView):
    serializer_class = TransportationSerializer

    def get_queryset(self, **kwargs):
        user_id = int(self.kwargs['user_id'])
        return Transportation.objects.filter(car_owner=self.kwargs['user_id']).order_by('data_created')
class getTransportationIdSortPriceF(generics.ListCreateAPIView):
    serializer_class = TransportationSerializer

    def get_queryset(self, **kwargs):
        user_id = int(self.kwargs['user_id'])
        return Transportation.objects.filter(car_owner=self.kwargs['user_id']).order_by('-price')
class getTransportationIdSortPriceI(generics.ListCreateAPIView):
    serializer_class = TransportationSerializer

    def get_queryset(self, **kwargs):
        user_id = int(self.kwargs['user_id'])
        return Transportation.objects.filter(car_owner=self.kwargs['user_id']).order_by('price')

class getTransportionsPriceI(generics.ListCreateAPIView):
    serializer_class = TransportationSerializer
    queryset = Transportation.objects.all().order_by('price')

class getTransportionsPriceF(generics.ListCreateAPIView):
    serializer_class = TransportationSerializer
    queryset = Transportation.objects.all().order_by('-price')

class getTransportionsSortOld(generics.ListCreateAPIView):
    serializer_class = TransportationSerializer
    queryset = Transportation.objects.all().order_by('data_created')

class TransportAPI(APIView):

    def get_object(self, pk):
        try:
            return Transportation.objects.get(pk=pk)
        except Transportation.DoesNotExist:
            raise Http404

    def get(self, request, pk, format=None):
        transport = self.get_object(pk)
        serializer = TransportationSerializer(transport)
        return Response(serializer.data)


    # def put(self, request, pk, format=None):
    #     user = self.get_object(pk)
    #     serializer = UserSerializer(user, data=request.data)
    #     if serializer.is_valid():
    #         serializer.save()
    #         return Response(serializer.data)
    #     return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
    #
    # def delete(self, request, pk, format=None):
    #     user = self.get_object(pk)
    #     user.delete()
    #     return Response(status=status.HTTP_204_NO_CONTENT)



def create_transportations(request):
    if request.user.is_authenticated:
        profile = Profile.objects.get(user_id=request.user.id)
    else:
        profile = None
    if request.method == 'POST':
        form = TransportationForm(request.POST)
        form.car_owner = request.user.username
        if form.is_valid():
            # cd = form.cleaned_data
            # user = authenticate(username=cd['username'], password=cd['password'])
            new_transportation = form.save(commit=False)
            new_transportation.car_owner = request.user
            if request.FILES:
                new_transportation.photo = request.FILES['photo']
            new_transportation.save()
            # transportations = Transportation.objects.all()
            transportations = Transportation.objects.all().order_by('-data_created')
            context = {
                'profile': profile,
                'transportations': transportations
            }
            return index(request, 1)
            # return render(request=request, template_name='transportation_car/main.html', context=context)
            # return render(request, 'account/register_done.html', {'new_user': "new_user"})
        else:
            return render(request, 'account/invalid_data.html', {'error': 'Invalid data!!!'})
            # return HttpResponse('Invalid login')
    else:
        form = TransportationForm()
    return render(request, 'transportation_car/create_transportations.html', {'form': form, 'profile': profile,})





