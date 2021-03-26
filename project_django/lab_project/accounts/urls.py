from django.urls import path

from .views import *


urlpatterns = [
    path('', index, name='choose_register'),
    path('login/', user_login, name='login'),
    path('logout/', user_logout, name='logout'),
    path('register/', register, name='register'),
    # path('login_app/', User_login_app.as_view(), name='user_login_app'),
]
