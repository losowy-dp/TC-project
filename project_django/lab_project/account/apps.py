from django.apps import AppConfig


class AccountsConfig(AppConfig):
    name = 'account'
    verbose_name = 'Konta'

    def ready(self):
        import signal