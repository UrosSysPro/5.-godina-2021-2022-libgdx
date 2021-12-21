
varying vec2 v_texCoords;
uniform sampler2D u_texture;

void main(){
    vec4 color=texture2D(u_texture,v_texCoords);
    float prosek=color.r+color.g+color.b;
    prosek/=float(3);

    gl_FragColor=vec4(prosek,prosek,prosek,1);
}