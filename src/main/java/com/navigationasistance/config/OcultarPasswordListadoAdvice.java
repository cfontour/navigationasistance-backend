package com.navigationasistance.config;

import com.navigationasistance.modelo.Usuario;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class OcultarPasswordListadoAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {

        if (!(body instanceof Collection)) {
            return body;
        }

        String path = request.getURI().getPath();
        if (path.endsWith("/")) {
            path = path.substring(0, path.length() - 1);
        }

        boolean esListado = path.endsWith("/usuarios/listar")
                || path.contains("/usuarios/listarGrupo/");

        if (!esListado) {
            return body;
        }

        List<Object> resultado = new ArrayList<>();
        for (Object item : (Collection<?>) body) {
            if (item instanceof Usuario) {
                resultado.add(copiaSinPassword((Usuario) item));
            } else if (item instanceof Map) {
                Map<Object, Object> copia = new LinkedHashMap<>((Map<?, ?>) item);
                copia.remove("password");
                resultado.add(copia);
            } else {
                resultado.add(item);
            }
        }
        return resultado;
    }

    // Copia nueva: no se modifica el objeto original (evita cualquier escritura accidental en la base)
    private Usuario copiaSinPassword(Usuario original) {
        Usuario copia = new Usuario();
        copia.setId(original.getId());
        copia.setNombre(original.getNombre());
        copia.setApellido(original.getApellido());
        copia.setEmail(original.getEmail());
        copia.setRol(original.getRol());
        copia.setTelefono(original.getTelefono());
        copia.setGrupoid(original.getGrupoid());
        return copia;
    }
}