

function getUrlVars() {
    var vars = [], hash;
    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
    for (var i = 0; i < hashes.length; i++) {
        hash = hashes[i].split('=');
        vars.push(hash[0]);
        vars[hash[0]] = hash[1];
    }
    return vars;
}


function fnToDate(value) {
    var myDate = new Date(parseInt(value.replace('/Date(', '')));
    return myDate.format("dd/MM/yyyy");
}

function dateFromString(strFecha_DDMMYYYY) {
    var fecha = strFecha_DDMMYYYY.replace("-", "/");
    var dia, mes, anio;
    dia = parseFloat(fecha.substring(0, 2));
    mes = parseFloat(fecha.substring(3, 5)) - 1;
    anio = parseFloat(fecha.substring(6, 10));
    return new Date(anio, mes, dia);
}


String.Format = function (b) {
    var a = arguments;
    return b.replace(/(\{\{\d\}\}|\{\d\})/g, function (b) {
        if (b.substring(0, 2) == "{{") return b;
        var c = parseInt(b.match(/\d/)[0]);
        return a[c + 1]
    })
};

function rowNumFormatter(value, row, index) {
    return 1 + index;
}

function MostrarAlertaSinFade(divName, nomClass, msg) {

    $("#" + divName).removeClass("faded").removeClass("out").removeClass("alert-danger").removeClass("alert-success").removeClass("alert-warning").removeClass("alert-info");
    $("#" + divName).addClass(nomClass);
    $("#" + divName).empty();
    $("#" + divName).append(msg);

}

function MostrarAlerta(divName, nomClass, msg) {

    $("#" + divName).removeClass("out").removeClass("alert-danger").removeClass("alert-success").removeClass("alert-warning").removeClass("alert-info");
    $("#" + divName).addClass(nomClass);
    $("#" + divName).addClass("in");
    $("#" + divName).empty();
    $("#" + divName).append(msg);

    window.setTimeout(function () {
        hideAlert(divName);
    }, 5000);
}

function hideAlert(alertName) {
    $("#" + alertName).addClass("out");
}

function LimpiarAlerta(divName) {

    $("#" + divName).removeClass("out").removeClass("alert-danger").removeClass("alert-success").removeClass("alert-warning").removeClass("alert-info");
    $("#" + divName).empty();

}

function PhoneField(b) {
    var a = document.all ? b.keyCode : b.which;
    if (a == 40 || a == 41 || a == 32 || a == 45 || a == 47 || a <= 13 || a >= 48 && a <= 57) {
        b.returnValue = true;
        return true;
    }
    else {
        b.returnValue = false;
        return false;
    }
}

function IsNumber(b) {
    var a = document.all ? b.keyCode : b.which;
    if (a <= 13 || a >= 48 && a <= 57) {
        b.returnValue = true;
        return true;
    }
    else {
        b.returnValue = false;
        return false;
    }
}

function IsDecimalNumber(b) {
    var a = document.all ? b.keyCode : b.which;
    if (a <= 13 || a >= 37 && a <= 40 || a >= 48 && a <= 57 || a == 46 || a == 190) {
        b.returnValue = true;
        return true;
    }
    else {
        b.returnValue = false;
        return false;
    }
}
function getPageName(url) {
    var index = url.lastIndexOf("/") + 1;
    var filenameWithExtension = url.substr(index);
    var filename = filenameWithExtension.split(".")[0]; // <-- added this line
    return filename;                                    // <-- added this line
}

/* center modal */
function centerModals() {
    $('.modal').each(function (i) {
        var $clone = $(this).clone().css('display', 'block').appendTo('body');
        var top = Math.round(($clone.height() - $clone.find('.modal-content').height()) / 2);
        top = top > 0 ? top : 0;
        $clone.remove();
        $(this).find('.modal-content').css("margin-top", top);
    });
}

$(window).on('resize', centerModals);


$(function () {
    //$('.modal').on('show.bs.modal', centerModals);
});

/*!
 * Validator v0.8.1 for Bootstrap 3, by @1000hz
 * Copyright 2015 Cina Saffary
 * Licensed under http://opensource.org/licenses/MIT
 *
 * https://github.com/1000hz/bootstrap-validator
 */

+function ($) {
    'use strict';

    var inputSelector = ':input:not([type="submit"], button):enabled:visible'
    // VALIDATOR CLASS DEFINITION
    // ==========================

    var Validator = function (element, options) {
        this.$element = $(element)
        this.options = options

        options.errors = $.extend({}, Validator.DEFAULTS.errors, options.errors)

        for (var custom in options.custom) {
            if (!options.errors[custom]) throw new Error('Missing default error message for custom validator: ' + custom)
        }

        $.extend(Validator.VALIDATORS, options.custom)

        this.$element.attr('novalidate', true) // disable automatic native validation
        this.toggleSubmit()

        this.$element.on('input.bs.validator change.bs.validator focusout.bs.validator', $.proxy(this.validateInput, this))
        this.$element.on('submit.bs.validator', $.proxy(this.onSubmit, this))

        this.$element.find('[data-match]').each(function () {
            var $this = $(this)
            var target = $this.data('match')

            $(target).on('input.bs.validator', function (e) {
                $this.val() && $this.trigger('input.bs.validator')
            })
        })
    }

    Validator.DEFAULTS = {
        delay: 500,
        html: false,
        disable: true,
        custom: {},
        errors: {
            match: 'Does not match',
            minlength: 'Not long enough'
        },
        feedback: {
            success: 'glyphicon-ok',
            error: 'glyphicon-remove'
        }
    }

    Validator.VALIDATORS = {
        native: function ($el) {
            var el = $el[0]
            return el.checkValidity ? el.checkValidity() : true
        },
        match: function ($el) {
            var target = $el.data('match')
            return !$el.val() || $el.val() === $(target).val()
        },
        minlength: function ($el) {
            var minlength = $el.data('minlength')
            return !$el.val() || $el.val().length >= minlength
        }
    }

    Validator.prototype.validateInput = function (e) {
        var $el = $(e.target)
        var prevErrors = $el.data('bs.validator.errors')
        var errors

        if ($el.is('[type="radio"]')) $el = this.$element.find('input[name="' + $el.attr('name') + '"]')

        this.$element.trigger(e = $.Event('validate.bs.validator', { relatedTarget: $el[0] }))

        if (e.isDefaultPrevented()) return

        var self = this

        this.runValidators($el).done(function (errors) {
            $el.data('bs.validator.errors', errors)

            errors.length ? self.showErrors($el) : self.clearErrors($el)

            if (!prevErrors || errors.toString() !== prevErrors.toString()) {
                e = errors.length
                  ? $.Event('invalid.bs.validator', { relatedTarget: $el[0], detail: errors })
                  : $.Event('valid.bs.validator', { relatedTarget: $el[0], detail: prevErrors })

                self.$element.trigger(e)
            }

            self.toggleSubmit()

            self.$element.trigger($.Event('validated.bs.validator', { relatedTarget: $el[0] }))
        })
    }


    Validator.prototype.runValidators = function ($el) {
        var errors = []
        var deferred = $.Deferred()
        var options = this.options

        $el.data('bs.validator.deferred') && $el.data('bs.validator.deferred').reject()
        $el.data('bs.validator.deferred', deferred)

        function getErrorMessage(key) {
            return $el.data(key + '-error')
              || $el.data('error')
              || key == 'native' && $el[0].validationMessage
              || options.errors[key]
        }

        $.each(Validator.VALIDATORS, $.proxy(function (key, validator) {
            if (($el.data(key) || key == 'native') && !validator.call(this, $el)) {
                var error = getErrorMessage(key)
                !~errors.indexOf(error) && errors.push(error)
            }
        }, this))

        if (!errors.length && $el.val() && $el.data('remote')) {
            this.defer($el, function () {
                var data = {}
                data[$el.attr('name')] = $el.val()
                $.get($el.data('remote'), data)
                  .fail(function (jqXHR, textStatus, error) { errors.push(getErrorMessage('remote') || error) })
                  .always(function () { deferred.resolve(errors) })
            })
        } else deferred.resolve(errors)

        return deferred.promise()
    }

    Validator.prototype.validate = function () {
        var delay = this.options.delay

        this.options.delay = 0
        this.$element.find(inputSelector).trigger('input.bs.validator')
        this.options.delay = delay

        return this
    }

    Validator.prototype.showErrors = function ($el) {
        var method = this.options.html ? 'html' : 'text'

        this.defer($el, function () {
            var $group = $el.closest('.form-group')
            var $block = $group.find('.help-block.with-errors')
            var $feedback = $group.find('.form-control-feedback')
            var errors = $el.data('bs.validator.errors')

            if (!errors.length) return

            errors = $('<ul/>')
              .addClass('list-unstyled')
              .append($.map(errors, function (error) { return $('<li/>')[method](error) }))

            $block.data('bs.validator.originalContent') === undefined && $block.data('bs.validator.originalContent', $block.html())
            $block.empty().append(errors)
            $group.addClass('has-error')

            $feedback.length
              && $feedback.removeClass(this.options.feedback.success)
              && $feedback.addClass(this.options.feedback.error)
              && $group.removeClass('has-success')
        })
    }

    Validator.prototype.clearErrors = function ($el) {
        var $group = $el.closest('.form-group')
        var $block = $group.find('.help-block.with-errors')
        var $feedback = $group.find('.form-control-feedback')

        $block.html($block.data('bs.validator.originalContent'))
        $group.removeClass('has-error')

        $feedback.length
          && $feedback.removeClass(this.options.feedback.error)
          && $feedback.addClass(this.options.feedback.success)
          && $group.addClass('has-success')
    }

    Validator.prototype.hasErrors = function () {
        function fieldErrors() {
            return !!($(this).data('bs.validator.errors') || []).length
        }

        return !!this.$element.find(inputSelector).filter(fieldErrors).length
    }

    Validator.prototype.isIncomplete = function () {
        function fieldIncomplete() {
            return this.type === 'checkbox' ? !this.checked :
                   this.type === 'radio' ? !$('[name="' + this.name + '"]:checked').length :
                                              $.trim(this.value) === ''
        }

        return !!this.$element.find(inputSelector).filter('[required]').filter(fieldIncomplete).length
    }

    Validator.prototype.onSubmit = function (e) {
        this.validate()
        if (this.isIncomplete() || this.hasErrors()) e.preventDefault()
    }

    Validator.prototype.toggleSubmit = function () {
        if (!this.options.disable) return
        var $btn = $('button[type="submit"], input[type="submit"]')
          .filter('[form="' + this.$element.attr('id') + '"]')
          .add(this.$element.find('input[type="submit"], button[type="submit"]'))
        $btn.toggleClass('disabled', this.isIncomplete() || this.hasErrors())
          .css({ 'pointer-events': 'all', 'cursor': 'pointer' })
    }

    Validator.prototype.defer = function ($el, callback) {
        callback = $.proxy(callback, this)
        if (!this.options.delay) return callback()
        window.clearTimeout($el.data('bs.validator.timeout'))
        $el.data('bs.validator.timeout', window.setTimeout(callback, this.options.delay))
    }

    Validator.prototype.destroy = function () {
        this.$element
          .removeAttr('novalidate')
          .removeData('bs.validator')
          .off('.bs.validator')

        this.$element.find(inputSelector)
          .off('.bs.validator')
          .removeData(['bs.validator.errors', 'bs.validator.deferred'])
          .each(function () {
              var $this = $(this)
              var timeout = $this.data('bs.validator.timeout')
              window.clearTimeout(timeout) && $this.removeData('bs.validator.timeout')
          })

        this.$element.find('.help-block.with-errors').each(function () {
            var $this = $(this)
            var originalContent = $this.data('bs.validator.originalContent')

            $this
              .removeData('bs.validator.originalContent')
              .html(originalContent)
        })

        this.$element.find('input[type="submit"], button[type="submit"]').removeClass('disabled')

        this.$element.find('.has-error').removeClass('has-error')
        this.$element.find('.has-success').removeClass('has-success')

        var $feedback = this.$element.find('.form-control-feedback')
        $feedback.removeClass('glyphicon-remove').removeClass('glyphicon-ok')
        return this
    }

    // VALIDATOR PLUGIN DEFINITION
    // ===========================


    function Plugin(option) {
        return this.each(function () {
            var $this = $(this)
            var options = $.extend({}, Validator.DEFAULTS, $this.data(), typeof option == 'object' && option)
            var data = $this.data('bs.validator')

            if (!data && option == 'destroy') return
            if (!data) $this.data('bs.validator', (data = new Validator(this, options)))
            if (typeof option == 'string') data[option]()
        })
    }

    var old = $.fn.validator

    $.fn.validator = Plugin
    $.fn.validator.Constructor = Validator


    // VALIDATOR NO CONFLICT
    // =====================

    $.fn.validator.noConflict = function () {
        $.fn.validator = old
        return this
    }


    // VALIDATOR DATA-API
    // ==================

    $(window).on('load', function () {
        $('form[data-toggle="validator"]').each(function () {
            var $form = $(this)
            Plugin.call($form, $form.data())
        })
    })

}(jQuery);
