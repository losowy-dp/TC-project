from email.mime.multipart import MIMEMultipart

from django.conf import settings
from django.shortcuts import render, redirect
from django.utils.http import urlsafe_base64_encode
# from requests import Response
from rest_framework.response import Response
from rest_framework import generics, status
from rest_framework.decorators import api_view
# from social_core.pipeline import user
from rest_framework.views import APIView

from .forms import LoginForm, RegistrationsForm, CheckEmailForm, ChangePassword, EditUserForm, EditNumberOfPhone, \
    ProfileForm
from django.contrib.auth import authenticate, login, logout, update_session_auth_hash
from django.http import HttpResponse, Http404
from .serializers import UserSerializer, ProfileSerializer, ProfileSer, UserSer
from django.contrib.auth.models import User
from .models import Profile
from django.contrib.auth.forms import PasswordResetForm, PasswordChangeForm
from django.db.models.query_utils import Q
from django.utils.encoding import force_bytes
from django.contrib.auth.tokens import default_token_generator
from django.template.loader import render_to_string
from django.core.mail import send_mail, BadHeaderError, EmailMessage
from django.contrib import messages

from transportation_car.models import Transportation


def index(request):
    return render(request, 'account/index.html')


def user_login(request):
    form_login = LoginForm()
    form_registration = RegistrationsForm()
    if 'login' in request.path.split('/'):
        key = False
    else:
        key = True

    if request.method == 'POST' and 'btnformlogin' in request.POST:
        form = LoginForm(request.POST)
        if form.is_valid():
            cd = form.cleaned_data
            user = authenticate(username=cd['username'], password=cd['password'])
            if user:
                login(request, user)
                return redirect('/transportations')
            else:
                return render(request, 'account/sign_in.html', {
                    'error': 'Hasło lub login jest niepoprawne',
                    'form_login': form,
                    'form_registration': form_registration,
                    'key': key}
                              )

    elif request.method == 'POST' and 'btnformregistration' in request.POST:
        key = True
        user_form = RegistrationsForm(request.POST)
        if user_form.is_valid():
            new_user = user_form.save(commit=False)
            new_user.set_password(user_form.cleaned_data['password'])
            new_user.save()
            # profile = Profile()
            # profile.user = new_user
            # profile.save()
            return render(request, 'account/register_done.html', {'new_user': new_user})
        else:
            return render(request, 'account/sign_in.html',
                          {'form_login': form_login, 'form_registration': user_form, 'key': key})

    return render(request, 'account/sign_in.html', {'form_login': form_login, 'form_registration': form_registration, 'key': key})


def userProfile(request, user_id):


    # transportations = Transportation.objects.filter().order_by('-data_created')
    # context = {
    #     'transportations': transportations[15 * (ile - 1):15 * ile],
    #     'length': transportations.count()
    # }


    user = User.objects.get(pk=user_id)
    profile = Profile.objects.get(user_id=user_id)
    form_change_photo = ProfileForm()

    transportations = Transportation.objects.filter(car_owner=user_id).order_by('-data_created')
    context = {
        'user': user,
        'profile': profile,
        'form_change_photo': form_change_photo,
        'transportations': transportations,
        'count': transportations.count(),
    }
    return render(request, 'account/profile.html', context)


def editUserProfile(request, user_id):
    user = User.objects.get(pk=user_id)
    profile = Profile.objects.get(user_id=user_id)
    # form_change_password = ChangePassword()
    form_change_password = PasswordChangeForm(user=request.user)
    form_edit_user_data = EditUserForm()
    form_edit_phone = EditNumberOfPhone()
    photo_form = ProfileForm()
    context = {
        'user': user,
        'profile': profile,
        'photoForm': photo_form,
        'form_change_password': form_change_password,
        'form_main': form_edit_user_data,
        'form_number': form_edit_phone,
    }
    return render(request, 'account/edit_profile.html', context)

def editDataProfile(request, user_id):
    user = User.objects.get(pk=user_id)
    profile = Profile.objects.get(user_id=user_id)
    # form_change_password = ChangePassword()
    form_edit_user_data = EditUserForm(request.POST, request.FILES)
    form_edit_phone = EditNumberOfPhone()
    # form_change_password = ChangePassword()
    form_change_password = PasswordChangeForm(user=request.user)
    photo_form = ProfileForm()
    if form_edit_user_data.is_valid():
        user.first_name =  form_edit_user_data.cleaned_data['first_name']
        user.last_name = form_edit_user_data.cleaned_data['last_name']
        profile.birthday = form_edit_user_data.cleaned_data['birthday']
        profile.photo = form_edit_user_data.cleaned_data['photo']
        user.save()
        profile.save()
    else:
        pass
    context = {
        'user': user,
        'profile': profile,
        'photoForm': photo_form,
        'form_change_password': form_change_password,
        'form_main': form_edit_user_data,
        'form_number': form_edit_phone,
    }
    return render(request, 'account/edit_profile.html', context)


def editNumberOfPhone(request, user_id):
    user = User.objects.get(pk=user_id)
    profile = Profile.objects.get(user_id=user_id)
    # form_change_password = ChangePassword()
    form_edit_user_data = EditUserForm()
    form_edit_phone = EditNumberOfPhone(request.POST)
    # form_change_password = ChangePassword()
    form_change_password = PasswordChangeForm(user=request.user)
    photo_form = ProfileForm()
    if form_edit_phone.is_valid():
        # user.first_name =  form_edit_user_data.cleaned_data['first_name']
        # user.last_name = form_edit_user_data.cleaned_data['last_name']
        profile.number_of_phone = form_edit_phone.cleaned_data['number_of_phone']
        # user.save()
        profile.save()
    else:
        pass
    context = {
        'user': user,
        'profile': profile,
        'photoForm': photo_form,
        'form_change_password': form_change_password,
        'form_main': form_edit_user_data,
        'form_number': form_edit_phone,
    }
    return render(request, 'account/edit_profile.html', context)


def editPhoto(request, user_id):
    user = User.objects.get(pk=user_id)
    profile = Profile.objects.get(user_id=user_id)
    photo_form = ProfileForm(request.POST, request.FILES)
    if photo_form.is_valid():
        profile.photo = request.FILES['photo']
        profile.save()
        photo_form.save()
    else:
        pass
    context = {
        'user': user,
        'profile': profile,
        'form_change_photo': photo_form,
    }
    return render(request, 'account/profile.html', context)

def changePassword(request, user_id):
    form_edit_user_data = EditUserForm()
    form_edit_phone = EditNumberOfPhone()
    user = User.objects.get(pk=user_id)
    profile = Profile.objects.get(user_id=user_id)
    form_change_password = PasswordChangeForm(user=request.user, data=request.POST)
        # ChangePassword(request.POST)
    photo_form = ProfileForm()
    if(form_change_password.is_valid()):
        # check_user = authenticate(username=user.username, password=form_change_password.cleaned_data['old_password'])
        # print("kostia - 1")
        # if (check_user is not None):
        #     print("kostia - 2")
        #     data = form_change_password.cleaned_data['new_password1']
        #     user.set_password(data)
        #     user.save()
        if(form_change_password.cleaned_data['new_password1'] != form_change_password.cleaned_data['old_password']):
            print("1")
            form_change_password.save()
            update_session_auth_hash(request, form_change_password.user)
            form = PasswordChangeForm(user=request.user)
                # ChangePassword()
            context = {
                'user': user,
                'profile': profile,
                'photoForm': photo_form,
                'form_change_password': form,
                'form_main': form_edit_user_data,
                'form_number': form_edit_phone,
            }

            return render(request, 'account/edit_profile.html', context)
        else:
            context = {
                'user': user,
                'profile': profile,
                'photoForm': photo_form,
                'form_change_password': form_change_password,
                'error': 'Nowe hasło takie samo jak stare.',
                'form_main': form_edit_user_data,
                'form_number': form_edit_phone,
            }
        return render(request, 'account/edit_profile.html', context)
    else:
        context = {
            'user': user,
            'profile': profile,
            'photoForm': photo_form,
            'form_change_password': form_change_password,
            'form_main': form_edit_user_data,
            'form_number': form_edit_phone,
        }
        return render(request, 'account/edit_profile.html', context)

def user_logout(request):
    logout(request)
    return redirect('/transportations')

def reset_password(request):
    if request.method == 'POST':
        password_reset_form = PasswordResetForm(request.POST)
        if password_reset_form.is_valid():
            data = password_reset_form.cleaned_data['email']
            associated_users = User.objects.filter(Q(email=data))
            if associated_users.exists():
                for user in associated_users:
                    subject = 'Password Reset Requested'
                    email_template_name = 'account/reset_password/password_reset_email.txt'
                    c = {
                        'email': user.email,
                        'domain': '6adca8826647.ngrok.io',
                        'site_name': 'CarTransportation',
                        'uid': urlsafe_base64_encode(force_bytes(user.pk)),
                        'user': user,
                        'token': default_token_generator.make_token(user),
                        'protocol': 'http',
                    }
                    email = render_to_string(email_template_name, c)
                    try:
                        send_mail(subject, email, settings.EMAIL_HOST_USER, [user.email])
                    except BadHeaderError:
                        return HttpResponse('Invalid header found.')
                    messages.success(request, 'A message with reset password instructions has been sent to your inbox.')
                    return redirect('/account/login')
                    # return redirect('done/')
            else:
                password_reset_form = PasswordResetForm(request.POST)
                return render(request=request, template_name='account/reset_password/password_reset.html', context={"password_reset_form": password_reset_form, 'error': 'Uzutkownika z takim emailem nie istnieje.'})
    password_reset_form = PasswordResetForm()
    return render(request=request, template_name='account/reset_password/password_reset.html', context={"password_reset_form": password_reset_form})

class getUser(generics.ListCreateAPIView):
    serializer_class = UserSerializer

    def get_queryset(self, **kwargs):
        return User.objects.filter(pk=self.kwargs['id'])

class getProfile(generics.ListCreateAPIView):
    serializer_class = ProfileSerializer

    def get_queryset(self, **kwargs):
        user_id = int(self.kwargs['user_id'])
        # user = User.objects.get(pk=user_id)
        # model_instance = Profile.objects.get_or_create(user=user)
        return Profile.objects.filter(user_id=self.kwargs['user_id'])

@api_view(['POST'])
def editProfile(request, user_id):
    if request.method == 'POST':
        print("kostia", request.data)
        user = User.objects.get(pk=user_id)
        serializer = ProfileSerializer(data=request.data)
        if serializer.is_valid():
            serializer.update()
        return Response(serializer.data, status=status.HTTP_200_OK)

@api_view(['POST'])
def editPr(request):
    if request.method == 'POST':
        serializer = ProfileSer(data=request.data)
        if serializer.is_valid():
            serializer.save()
        return Response(serializer.data, status=status.HTTP_200_OK)


class UserList(generics.ListAPIView):
    queryset = User.objects.all()
    serializer_class = UserSer


class UserDetail(generics.RetrieveAPIView):
    queryset = User.objects.all()
    serializer_class = UserSer


class UserAPI(APIView):
    """
    Retrieve, update or delete a snippet instance.
    """
    def get_object(self, pk):
        try:
            return User.objects.get(pk=pk)
        except User.DoesNotExist:
            raise Http404

    def get(self, request, pk, format=None):
        user = self.get_object(pk)
        serializer = UserSerializer(user)
        return Response(serializer.data)

    def put(self, request, pk, format=None):
        user = self.get_object(pk)
        serializer = UserSerializer(user, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, pk, format=None):
        user = self.get_object(pk)
        user.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)



class ProfileAPI(APIView):
    """
    Retrieve, update or delete a snippet instance.
    """
    def get_object(self, pk):
        try:
            return Profile.objects.get(user_id=pk)
        except User.DoesNotExist:
            raise Http404

    def get(self, request, pk, format=None):
        profile = self.get_object(pk)
        serializer = ProfileSerializer(profile)
        return Response(serializer.data)

    def put(self, request, pk, format=None):
        profile = self.get_object(pk)
        serializer = ProfileSerializer(profile, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, pk, format=None):
        profile = self.get_object(pk)
        profile.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)


