import os
import javalang

def get_visibility(modifiers):
    if 'public' in modifiers:
        return '+'
    elif 'private' in modifiers:
        return '-'
    elif 'protected' in modifiers:
        return '#'
    else:
        return '~'  # package-private (default)

def parse_java_file(filepath):
    with open(filepath, 'r', encoding='utf-8') as file:
        code = file.read()

    tree = javalang.parse.parse(code)
    classes = []

    for type_decl in tree.types:
        is_interface = isinstance(type_decl, javalang.tree.InterfaceDeclaration)
        is_class = isinstance(type_decl, javalang.tree.ClassDeclaration)

        if is_interface or is_class:
            class_info = {
                'name': type_decl.name,
                'type': 'interface' if is_interface else 'class',
                'extends': type_decl.extends.name if type_decl.extends else None,
                'implements': [i.name for i in type_decl.implements] if is_class and type_decl.implements else [],
                'fields': [],
                'methods': [],
                'associations': []
            }

            for member in type_decl.body:
                if isinstance(member, javalang.tree.FieldDeclaration):
                    visibility = get_visibility(member.modifiers)
                    for decl in member.declarators:
                        field_type = getattr(member.type, 'name', 'Object')
                        full_type = member.type
                        assoc_detected = False

                        # Detectar associações do tipo List<Tipo> ou Set<Tipo>
                        if hasattr(full_type, 'arguments') and full_type.arguments:
                            try:
                                arg_type = full_type.arguments[0].type.name
                                assoc = f'{class_info["name"]} "1" --> "*" {arg_type}'
                                class_info['associations'].append(assoc)
                                assoc_detected = True
                            except Exception:
                                pass  # Falha ao tentar extrair o tipo genérico

                        # Caso o campo seja direto (ex: Emprestimo emprestimo;)
                        elif hasattr(full_type, 'name') and full_type.name not in ['int', 'float', 'double', 'boolean', 'char', 'long', 'String']:
                            assoc = f'{class_info["name"]} "1" --> "1" {full_type.name}'
                            class_info['associations'].append(assoc)

                        field = f"{visibility} {decl.name} : {field_type}"
                        class_info['fields'].append(field)

                elif isinstance(member, javalang.tree.MethodDeclaration):
                    visibility = get_visibility(member.modifiers)
                    params = ', '.join([f"{p.type.name}" for p in member.parameters])
                    return_type = member.return_type.name if member.return_type else 'void'
                    method = f"{visibility} {member.name}({params}) : {return_type}"
                    class_info['methods'].append(method)

            classes.append(class_info)

    return classes

def generate_puml(classes, output_file):
    with open(output_file, 'w', encoding='utf-8') as f:
        f.write("@startuml\n\n")
        for cls in classes:
            f.write(f"{cls['type']} {cls['name']} {{\n")
            for field in cls['fields']:
                f.write(f"    {field}\n")
            for method in cls['methods']:
                f.write(f"    {method}\n")
            f.write("}\n\n")

        for cls in classes:
            if cls['extends']:
                f.write(f"{cls['extends']} <|-- {cls['name']}\n")
            for impl in cls['implements']:
                f.write(f"{impl} <|.. {cls['name']}\n")
            for assoc in cls['associations']:
                f.write(f"{assoc}\n")

        f.write("\n@enduml\n")

def process_directory(directory):
    all_classes = []
    for root, _, files in os.walk(directory):
        for file in files:
            if file.endswith('.java'):
                path = os.path.join(root, file)
                try:
                    parsed = parse_java_file(path)
                    all_classes.extend(parsed)
                except Exception as e:
                    print(f"[!] Erro em {file}: {e}")
    return all_classes

if __name__ == '__main__':
    import argparse

    parser = argparse.ArgumentParser(description="Gerar PlantUML a partir de código Java")
    parser.add_argument('--input', '-i', required=True, help='Diretório com código Java')
    parser.add_argument('--output', '-o', default='diagrama.puml', help='Arquivo de saída PlantUML')
    args = parser.parse_args()

    print(f"[+] Analisando {args.input} ...")
    classes = process_directory(args.input)
    print(f"[+] {len(classes)} classes/interfaces encontradas.")
    print(f"[+] Gerando {args.output} ...")
    generate_puml(classes, args.output)
    print("[✓] Pronto!")
